package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.auth.OtpSignInState
import com.sportzinteractive.baseprojectsetup.data.model.auth.SignInOtpRequest
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInWithOTP @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(signInOtpRequest: SignInOtpRequest,url:String): Flow<OtpSignInState> {
        return authRepository.otpLogin(signInOtpRequest,url)
    }

}