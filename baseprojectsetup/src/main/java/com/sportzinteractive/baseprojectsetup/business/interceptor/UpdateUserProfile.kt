package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.auth.UpdateUserProfileRequest
import com.sportzinteractive.baseprojectsetup.data.model.auth.User
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserProfile @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(updateUserProfileRequest: UpdateUserProfileRequest, url:String): Flow<Resource<User?>> {
        return authRepository.updateUserProfile(updateUserProfileRequest,url)
    }
}