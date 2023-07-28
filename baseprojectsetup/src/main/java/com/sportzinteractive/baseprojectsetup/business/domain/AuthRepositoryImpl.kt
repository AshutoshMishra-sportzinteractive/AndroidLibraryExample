package com.sportzinteractive.baseprojectsetup.business.domain

import com.sportzinteractive.baseprojectsetup.data.mapper.auth.UserEntityMapper
import com.sportzinteractive.baseprojectsetup.data.model.AuthBaseRequest
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponseData
import com.sportzinteractive.baseprojectsetup.data.model.auth.OtpSignInState
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignInOtpRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.User
import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailMobileOTPRequest
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import com.sportzinteractive.baseprojectsetup.data.services.AuthService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.*
import com.sportzinteractive.baseprojectsetup.utils.Constants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ViewModelScoped
class AuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val authService: AuthService,
    private val userEntityMapper: UserEntityMapper,
    private val baseLocalStorageManager: BaseLocalStorageManager
):AuthRepository {


    override fun sendOTP(
        sendEmailMobileOTPRequest: SendEmailMobileOTPRequest,
        url:String
    ): Flow<Resource<OTPResponse?>> {
        return flow {
            //val url = "https://stg-rr.sportz.io/apiv3/auth/sendotp?is_app=1"
            val result = safeApiCall(dispatcher) {
                authService.sendOTP(
                    url,
                    AuthBaseRequest(
                        sendEmailMobileOTPRequest
                    )
                )
            }
            val resource =
                object :
                    ApiResultHandler<BaseResponseData<OTPResponse>, OTPResponse?>(result) {
                    override suspend fun handleSuccess(resultObj: BaseResponseData<OTPResponse>): Resource<OTPResponse?> {
                        return if (resultObj.status == 200) {
                            return Resource.Success(resultObj.data)
                        } else {
                            Resource.Error(
                                NetworkThrowable(
                                    resultObj.status,
                                    resultObj.status.toString()
                                )
                            )
                        }
                    }
                }.getResult()
            emit(resource)
        }
    }

    override fun verifyOTP(
        sendEmailMobileOTPRequest: SendEmailMobileOTPRequest,
        url:String
    ): Flow<Resource<OTPResponse?>> {
        return flow {
            //val url = "https://stg-rr.sportz.io/apiv3/auth/verifyotp?is_app=1"
            val result = safeApiCall(dispatcher) {
                authService.verifyOTP(
                    url,
                    AuthBaseRequest(
                        sendEmailMobileOTPRequest
                    )
                )
            }
            val resource =
                object :
                    ApiResultHandler<BaseResponseData<OTPResponse>, OTPResponse?>(result) {
                    override suspend fun handleSuccess(resultObj: BaseResponseData<OTPResponse>): Resource<OTPResponse?> {
                        //"0" failed,"2" attempt exceeded "1" OTP verified
                        return if (resultObj.status == 200) {
                            return Resource.Success(resultObj.data)
                        } else {
                            Resource.Error(
                                NetworkThrowable(
                                    resultObj.status,
                                    resultObj.status.toString()
                                )
                            )
                        }
                    }
                }.getResult()
            emit(resource)
        }
    }

    override fun otpLogin(signInOtpRequest: SignInOtpRequest, url: String): Flow<OtpSignInState> {
        return flow {
//            val url = "https://stg-rr.sportz.io/apiv3/auth/signinwithotp"
            val result = safeApiCall(dispatcher) {
                authService.signInWithOtp(
                    url = url,
                    signInOtpRequest = AuthBaseRequest(
                        signInOtpRequest
                    )
                )
            }
            when (result) {
                is ApiResult.GenericError -> emit(OtpSignInState.Failure(result.message ?: ""))
                is ApiResult.NetworkError -> emit(OtpSignInState.Failure(result.message ?: ""))
                is ApiResult.Success -> {
                    val retrofitResponse = result.data
                    val body = result.data?.body()
                    val data = body?.responseData
                    if (body?.status == GenericStatusCode.SUCCESS.statusCode && data != null) {
                        when (data.status) {
                            AuthApiStatus.SUCCESS.statusCode, AuthApiStatus.DELETE_REQUEST_ACCOUNT.statusCode -> {
                                val userTokenNullable = retrofitResponse
                                    ?.headers()?.get(Constants.USER_TOKEN)
                                if (data.userGuid != null && data.user != null && userTokenNullable != null) {
                                    baseLocalStorageManager.setCompleteProfile(true)
                                    saveUserInfo(
                                        userGuid = data.userGuid,
                                        userToken = userTokenNullable,
                                        user = userEntityMapper.toDomain(
                                            entity = data.user,
                                            email = data.email,
                                            accountCreateDateTime = data.createdDateTime,
                                            status = data.status
                                        ),
                                        userWafId = data.waf_id?:""
                                    )
                                    emit(OtpSignInState.Success(data.message ?: ""))
                                } else {
                                    emit(OtpSignInState.Failure("Some required user data is missing from server end!"))
                                }
                            }
                            AuthApiStatus.INCOMPLETE_USER_DATA.statusCode -> {
                                val userTokenNullable = retrofitResponse
                                    ?.headers()?.get(Constants.USER_TOKEN)
                                if (data.userGuid != null && data.user != null && userTokenNullable != null) {
                                    baseLocalStorageManager.setCompleteProfile(false)
                                    saveUserInfo(
                                        userGuid = data.userGuid,
                                        userToken = userTokenNullable,
                                        user = userEntityMapper.toDomain(
                                            entity = data.user,
                                            email = data.email,
                                            accountCreateDateTime = data.createdDateTime,
                                            status = data.status
                                        ),
                                        userWafId = data.waf_id?:""
                                    )
                                    setIsOtpRequired(
                                        data.emailVerified ?: "",
                                        data.mobileVerified ?: ""
                                    )
                                    emit(OtpSignInState.IncompleteProfile(data.message ?: ""))
                                } else {
                                    emit(OtpSignInState.Failure("Some required user data is missing from server end!"))
                                }
                            }
                            else -> {
                                baseLocalStorageManager.removeAll()
                                emit(OtpSignInState.Failure(data.message ?: ""))
                            }
                        }
                    } else {
                        baseLocalStorageManager.removeAll()
                        emit(OtpSignInState.Failure(body?.status.toString()))
                    }
                }
            }
        }
    }

    private suspend fun saveUserInfo(userGuid: String, userToken: String, user: User, userWafId:String) {
        baseLocalStorageManager.setUserGuid(userGuid)
        baseLocalStorageManager.setUserWafId(userWafId)
        baseLocalStorageManager.setUserToken(userToken)
        baseLocalStorageManager.setUser(user)
    }

    private suspend fun setIsOtpRequired(emailVerified: String, mobileVerified: String) {
        if (emailVerified == "1") {
            baseLocalStorageManager.setEmailVerified(true)
        } else {
            baseLocalStorageManager.setEmailVerified(false)
        }
        if (mobileVerified == "1") {
            baseLocalStorageManager.setMobileVerified(true)
        } else {
            baseLocalStorageManager.setMobileVerified(false)
        }

    }
}