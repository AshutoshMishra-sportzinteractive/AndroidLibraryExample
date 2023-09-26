package com.sportzinteractive.baseprojectsetup.data.repository

import com.sportzinteractive.baseprojectsetup.data.model.account.AccountDeleteResponse
import com.sportzinteractive.baseprojectsetup.data.model.account.AccountResponse
import com.sportzinteractive.baseprojectsetup.data.model.auth.*
import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailMobileOTPRequest
import com.sportzinteractive.baseprojectsetup.data.model.verifyemail.VerifyEmailResponse
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {


    fun sendOTP(
        sendEmailMobileOTPRequest: SendEmailMobileOTPRequest,
        url: String
    ): Flow<Resource<OTPResponse?>>

    fun verifyOTP(
        sendEmailMobileOTPRequest: SendEmailMobileOTPRequest,
        url: String
    ): Flow<Resource<OTPResponse?>>

    fun otpLogin(signInOtpRequest: SignInOtpRequest, url: String): Flow<OtpSignInState>

    fun updateUserProfile(
        updateUserProfileRequest: UpdateUserProfileRequest,
        url: String
    ): Flow<Resource<User?>>

    fun getUserProfile(forceFetch: Boolean, url: String): Flow<Resource<User?>>

    fun signOut(captcha: String, url: String): Flow<SignOutState>

    fun deleteAccount(url: String): Flow<Resource<AccountDeleteResponse?>>

    fun restoreAccount(url: String): Flow<Resource<AccountResponse?>>

    fun verifyEmail(userEmail: String,url: String): Flow<Resource<VerifyEmailResponse?>>

}