package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.model.CountryListState
import com.sportzinteractive.baseprojectsetup.data.model.state.State
import com.sportzinteractive.baseprojectsetup.data.model.state.StateObject
import com.sportzinteractive.baseprojectsetup.data.repository.BaseRepository
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetStateList @Inject constructor(
    private val baseRepository: BaseRepository
) {
    operator fun invoke(url:String): Flow<Resource<List<StateObject?>>> {
        return baseRepository.rawBaseApiCallGet<State>(url).map {
            when(it){
                is Resource.Error -> Resource.Error(throwable = it.throwable)
                is Resource.Loading -> Resource.Loading()
                else -> {
                    Resource.Success(it?.data?.body()?.content?.states)
                }
            }
        }

    }
}