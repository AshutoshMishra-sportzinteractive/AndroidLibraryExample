package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.otp.OTPResponse
import com.sportzinteractive.baseprojectsetup.data.model.otp.SendEmailMobileOTPRequest
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class VerifyOTP @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        sendEmailMobileOTPRequest: SendEmailMobileOTPRequest,
        url:String
    ): Flow<Resource<OTPResponse?>> {
        return authRepository.verifyOTP(sendEmailMobileOTPRequest,url)
    }
}
