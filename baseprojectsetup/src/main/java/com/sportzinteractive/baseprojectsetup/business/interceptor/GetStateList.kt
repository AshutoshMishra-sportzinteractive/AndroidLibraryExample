package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.data.model.state.StateObject
import com.sportzinteractive.baseprojectsetup.data.repository.GeneralRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetStateList @Inject constructor(
    private val generalRepository: GeneralRepository
) {
    operator fun invoke(countryId:String): Flow<Resource<List<StateObject?>>> {
        return generalRepository.getStateList(countryId)
    }
}