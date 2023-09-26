package com.sportzinteractive.baseprojectsetup.data.services

import com.sportzinteractive.baseprojectsetup.data.model.AuthBaseRequest
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponseData
import com.sportzinteractive.baseprojectsetup.data.model.account.AccountDeleteResponse
import com.sportzinteractive.baseprojectsetup.data.model.account.AccountResponse
import com.sportzinteractive.baseprojectsetup.data.model.account.SendAccountRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.AuthBaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignInOtpRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignOutRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.UpdateUserProfileRequest
import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailMobileOTPRequest
import com.sportzinteractive.baseprojectsetup.data.model.verifyemail.VerifyEmailResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthService {


    @POST
    suspend fun sendOTP(
        @Url url: String,
        @Body sendEmailOTPRequest: AuthBaseRequest<SendEmailMobileOTPRequest>,
    ): BaseResponseData<OTPResponse>

    @POST
    suspend fun verifyOTP(
        @Url url: String,
        @Body sendEmailOTPRequest: AuthBaseRequest<SendEmailMobileOTPRequest>,
    ): BaseResponseData<OTPResponse>

    @POST
    suspend fun signInWithOtp(
        @Url url: String,
        @Body signInOtpRequest: AuthBaseRequest<SignInOtpRequest>,
    ): Response<AuthBaseResponse>

    @POST
    suspend fun updateUserProfile(
        @Url url: String,
        @Body updateUserProfileRequest: AuthBaseRequest<UpdateUserProfileRequest>
    ): Response<AuthBaseResponse>

    @GET
    suspend fun getProfile(
        @Url url: String
    ): AuthBaseResponse

    @POST
    suspend fun signOut(
        @Url url: String,
        @Body signOutRequest: AuthBaseRequest<SignOutRequest>,
    ): AuthBaseResponse

    @GET
    suspend fun downloadUrl(
        @Url url: String
    ): ResponseBody

    @POST
    suspend fun deleteAccount(
        @Url url: String,
        @Body sendEmailOTPRequest: AuthBaseRequest<SendAccountRequest>,
    ): AccountDeleteResponse

    @POST
    suspend fun restoreAccount(
        @Url url: String,
        @Body sendEmailOTPRequest: AuthBaseRequest<SendAccountRequest>,
    ): BaseResponseData<AccountResponse>


    @GET
    suspend fun verifyEmail(
        @Url url: String
    ): BaseResponse<VerifyEmailResponse>


}