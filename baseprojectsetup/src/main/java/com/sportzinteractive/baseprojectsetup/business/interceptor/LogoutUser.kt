package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.auth.SignOutState
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class LogoutUser @Inject constructor(
    private val authRepository: AuthRepository,
) {

    operator fun invoke(captcha: String, url: String): Flow<SignOutState> {
        return authRepository.signOut(captcha, url)
    }

}