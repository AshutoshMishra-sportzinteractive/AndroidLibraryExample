package com.sportzinteractive.baseprojectsetup.business.domain.repository

import android.util.Log
import com.sportzinteractive.baseprojectsetup.business.domain.AuthApiStatus
import com.sportzinteractive.baseprojectsetup.business.domain.GenericStatusCode
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.mapper.auth.UserEntityMapper
import com.sportzinteractive.baseprojectsetup.data.model.AuthBaseRequest
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponseData
import com.sportzinteractive.baseprojectsetup.data.model.account.AccountDeleteResponse
import com.sportzinteractive.baseprojectsetup.data.model.account.AccountResponse
import com.sportzinteractive.baseprojectsetup.data.model.account.SendAccountRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.AuthBaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.auth.OtpSignInState
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignInOtpRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignOutRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignOutState
import com.sportzinteractive.baseprojectsetup.data.model.auth.UpdateUserProfileRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.User
import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailMobileOTPRequest
import com.sportzinteractive.baseprojectsetup.data.model.verifyemail.VerifyEmailResponse
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import com.sportzinteractive.baseprojectsetup.data.services.AuthService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.ApiResult
import com.sportzinteractive.baseprojectsetup.helper.ApiResultHandler
import com.sportzinteractive.baseprojectsetup.helper.BaseLocalStorageManager
import com.sportzinteractive.baseprojectsetup.helper.NetworkThrowable
import com.sportzinteractive.baseprojectsetup.helper.Resource
import com.sportzinteractive.baseprojectsetup.helper.networkBoundResource
import com.sportzinteractive.baseprojectsetup.helper.safeApiCall
import com.sportzinteractive.baseprojectsetup.utils.Constants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


@ViewModelScoped
class AuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val authService: AuthService,
    private val userEntityMapper: UserEntityMapper,
    private val baseLocalStorageManager: BaseLocalStorageManager,
    private val baseInfo: BaseInfo
) : AuthRepository {


    override fun sendOTP(
        sendEmailMobileOTPRequest: SendEmailMobileOTPRequest,
        url:String
    ): Flow<Resource<OTPResponse?>> {
        return flow {
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
                                if (data.userGuid != null && data.user != null) {
                                    baseLocalStorageManager.setCompleteProfile(true)
                                    saveUserInfo(
                                        userGuid = data.userGuid,
                                        userToken = userTokenNullable?:"",
                                        user = userEntityMapper.toDomain(
                                            entity = data.user,
                                            email = data.email,
                                            status = data.status
                                        ),
                                        userWafId = data.waf_id?:""
                                    )
                                    data.user.extInfo?.clubs?.distinct()?.joinToString ( ",")
                                        ?.let { baseLocalStorageManager.setTeamIdFollowTeamId(it) }
                                    baseLocalStorageManager.setIsNotificationFirstTime(true)
                                    emit(OtpSignInState.Success(data.message ?: "", data.mobileVerified.equals("1")))
                                } else {
                                    emit(OtpSignInState.Failure(data.message ?: ""))
                                }
                            }
                            AuthApiStatus.INCOMPLETE_USER_DATA.statusCode -> {
                                val userTokenNullable = retrofitResponse
                                    ?.headers()?.get(Constants.USER_TOKEN)
                                if (data.userGuid != null && data.user != null) {
                                    baseLocalStorageManager.setCompleteProfile(false)
                                    saveUserInfo(
                                        userGuid = data.userGuid,
                                        userToken = userTokenNullable?:"",
                                        user = userEntityMapper.toDomain(
                                            entity = data.user,
                                            email = data.email,
                                            status = data.status
                                        ),
                                        userWafId = data.waf_id ?: ""
                                    )
                                    setIsOtpRequired(
                                        data.emailVerified ?: "", data.mobileVerified ?: ""
                                    )
                                    emit(OtpSignInState.IncompleteProfile(data.message ?: "",data.mobileVerified.equals("1")))
                                } else {
                                    emit(OtpSignInState.Failure(data.message?:""))
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

    override fun updateUserProfile(
        updateUserProfileRequest: UpdateUserProfileRequest,
        url: String
    ): Flow<Resource<User?>> {
        return flow {
            val result = safeApiCall(dispatcher){
                authService.updateUserProfile(
                    url,
                    updateUserProfileRequest = AuthBaseRequest(updateUserProfileRequest)
                )
            }
            when (result) {
                is ApiResult.GenericError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            result.code,
                            result.message.toString()
                        )
                    )
                )
                is ApiResult.NetworkError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            null,
                            result.message.toString()
                        )
                    )
                )
                is ApiResult.Success -> {
                    val resource =
                        object : ApiResultHandler<Response<AuthBaseResponse>, User>(result) {
                            override suspend fun handleSuccess(resultObj: Response<AuthBaseResponse>): Resource<User?> {
                                val retrofitResponse = result.data
                                val body = result.data?.body()
                                val errorCode = result.data?.code()
                                val data = body?.responseData

                                return if (data?.status == 1) {
                                    val userEntity = data?.user
                                    if (userEntity != null) {
                                        val userTokenNullable =
                                            retrofitResponse?.headers()?.get(Constants.USER_TOKEN)
                                        val user = userEntityMapper.toDomain(
                                            entity = data.user,
                                            email = data.email,
                                            status = data.status
                                        )
                                        data.user.extInfo?.clubs?.distinct()?.joinToString ( ",")
                                            ?.let { baseLocalStorageManager.setTeamIdFollowTeamId(it) }
                                        baseLocalStorageManager.setIsNotificationFirstTime(true)
                                        Log.d("updateResponse", "user = $user raw = $userEntity")
                                        if (data.userGuid != null) {
                                            baseLocalStorageManager.setCompleteProfile(true)
                                            saveUserInfo(
                                                userGuid = data.userGuid,
                                                userToken = userTokenNullable?:"",
//                                                userToken = baseLocalStorageManager.getUserToken().firstOrNull()?:"",
                                                user = user,
                                                userWafId = data.waf_id?:""
                                            )
                                        }
                                        Resource.Success(user)
                                    } else {
                                        Resource.Success(null)
                                    }
                                } else {
                                    Resource.Error(
                                        NetworkThrowable(
                                            errorCode,
                                            data?.message ?: ""
                                        )
                                    )
                                }
                            }
                        }.getResult()
                    emit(resource)
                }

            }
        }
    }

    override fun getUserProfile(forceFetch: Boolean, url: String): Flow<Resource<User?>> {
        return networkBoundResource(
            query = { baseLocalStorageManager.getUser() },
            fetch = {
                authService.getProfile(url)
            },
            saveFetchResult = { response ->
                if (response.status == GenericStatusCode.SUCCESS.statusCode) {
                    val userEntity = response.responseData?.user
                    val emailVerified = response.responseData?.emailVerified ?: ""
                    val mobileVerified = response.responseData?.mobileVerified ?: ""
                    if (userEntity != null) {
                        baseLocalStorageManager.setUser(
                            userEntityMapper.toDomain(
                                entity = userEntity,
                                email = response.responseData.email,
                                accountCreateDateTime = response.responseData.createdDateTime,
                                status = response.responseData.status
                            )
                        )
                    }
                    if (emailVerified.isNotEmpty() && emailVerified.equals("1")) {
                        baseLocalStorageManager.setEmailVerified(true)
                    } else {
                        baseLocalStorageManager.setEmailVerified(false)
                    }
                    if (mobileVerified.isNotEmpty() && mobileVerified == "1") {
                        baseLocalStorageManager.setMobileVerified(true)
                    } else {
                        baseLocalStorageManager.setMobileVerified(false)
                    }
                }
            },
            shouldFetch = { forceFetch },
        )
    }

    override fun signOut(captcha: String, url: String): Flow<SignOutState> {
        return flow {
            val url = url
            val result = safeApiCall(dispatcher = dispatcher) {
                authService.signOut(
                    url, AuthBaseRequest(
                        SignOutRequest(
                            userGuid = baseLocalStorageManager.getUserGuid().firstOrNull()?.toString() ?: "",
                            isApp = "1",
                            captcha = captcha
                        )
                    )
                )
            }
            when (result) {
                is ApiResult.GenericError -> emit(SignOutState.Failure(result.message ?: ""))
                is ApiResult.NetworkError -> emit(SignOutState.Failure(result.message ?: ""))
                is ApiResult.Success -> {
                    if (result.data?.status == 200) {
                        if (result.data.responseData?.status == 1) {
                            emit(SignOutState.Success(result.data.responseData.message ?: ""))
                        } else {
                            emit(SignOutState.Failure(result.data.responseData?.message ?: ""))
                        }
                    } else {
                        emit(SignOutState.Failure("Something went wrong!!"))
                    }
                }
            }
        }
    }


    private suspend fun saveUserInfo(userGuid: String, userToken: String, user: User, userWafId:String) {
        baseLocalStorageManager.setUserGuid(userGuid)
        baseLocalStorageManager.setUserWafId(userWafId)
        baseLocalStorageManager.setUserToken(userToken)
        //baseLocalStorageManager.setToken(token)
        baseLocalStorageManager.setUser(user)
        //baseLocalStorageManager.setEpocTimeStamp(epochTimestamp)
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

    override fun deleteAccount(url: String): Flow<Resource<AccountDeleteResponse?>> {
        return flow {
            val result = safeApiCall(dispatcher) {
                authService.deleteAccount(
                    url, AuthBaseRequest(
                        SendAccountRequest(platform_version = baseInfo.getVersionName())
                    )
                )
            }
            val resource = object : ApiResultHandler<AccountDeleteResponse, AccountDeleteResponse?>(
                result
            ) {
                override suspend fun handleSuccess(resultObj: AccountDeleteResponse): Resource<AccountDeleteResponse?> {
                    return if (resultObj.status == 200) {
                        return Resource.Success(resultObj)
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                resultObj.status, "Something went wrong!!"
                            )
                        )
                    }
                }
            }.getResult()
            emit(resource)

        }
    }

    override fun restoreAccount(url: String): Flow<Resource<AccountResponse?>> {
        return flow {
            val result = safeApiCall(dispatcher) {
                authService.restoreAccount(
                    url, AuthBaseRequest(
                        SendAccountRequest(platform_version = baseInfo.getVersionName())
                    )
                )
            }
            val resource =
                object : ApiResultHandler<BaseResponseData<AccountResponse>, AccountResponse?>(
                    result
                ) {
                    override suspend fun handleSuccess(resultObj: BaseResponseData<AccountResponse>): Resource<AccountResponse?> {
                        return if (resultObj.status == 200) {
                            return Resource.Success(resultObj.data)
                        } else {
                            Resource.Error(
                                NetworkThrowable(
                                    resultObj.status, "Something went wrong!!"
                                )
                            )
                        }
                    }
                }.getResult()
            emit(resource)
        }
    }


    override fun verifyEmail(userEmail: String,url: String): Flow<Resource<VerifyEmailResponse?>> {
        return flow {
            //val url = configManager.verifyEmail(userEmail)
            val result = safeApiCall(dispatcher) {
                authService.verifyEmail(url)
            }
            val resource = object :
                ApiResultHandler<BaseResponse<VerifyEmailResponse>, VerifyEmailResponse?>(result) {
                override suspend fun handleSuccess(resultObj: BaseResponse<VerifyEmailResponse>): Resource<VerifyEmailResponse?> {
                    return if (resultObj.status == 200) {
                        return Resource.Success(resultObj.content)
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                resultObj.status, resultObj.status.toString()
                            )
                        )
                    }
                }
            }.getResult()
            emit(resource)
        }
    }
}