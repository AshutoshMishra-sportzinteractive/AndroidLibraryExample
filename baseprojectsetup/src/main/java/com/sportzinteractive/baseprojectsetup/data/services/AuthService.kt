package com.sportzinteractive.baseprojectsetup.data.services

import com.sportzinteractive.baseprojectsetup.data.model.AuthBaseRequest
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponseData
import com.sportzinteractive.baseprojectsetup.data.model.auth.AuthBaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignInOtpRequest
import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailMobileOTPRequest
import retrofit2.Response
import retrofit2.http.Body
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






}