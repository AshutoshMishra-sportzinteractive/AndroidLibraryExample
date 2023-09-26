package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.PlayerItem
import com.sportzinteractive.baseprojectsetup.data.repository.ListingRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSquadList @Inject constructor(
    private val authRepository: ListingRepository
) {
    operator fun invoke(url:String): Flow<Resource<List<PlayerItem>?>> {
        return authRepository.getFootballSquadsData(url)
    }
}