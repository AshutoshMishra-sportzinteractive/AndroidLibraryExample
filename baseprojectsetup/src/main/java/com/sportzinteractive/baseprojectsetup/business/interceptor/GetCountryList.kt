package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.domain.model.CountryListState
import com.sportzinteractive.baseprojectsetup.data.repository.GeneralRepository
import com.sportzinteractive.baseprojectsetup.di.DefaultDispatcher
import com.sportzinteractive.baseprojectsetup.helper.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetCountryList @Inject constructor(
    private val generalRepository: GeneralRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){

    operator fun invoke(url:String,defaultCountryCode:String="91"): Flow<Resource<CountryListState>> {
        return generalRepository.getCountryList(url).map {
            when(it) {
                is Resource.Error -> Resource.Error(throwable = it.throwable)
                is Resource.Loading -> Resource.Loading()
                else -> {
                    val filteredList = it.data?.filter { e -> e.phoneCode!=0 }
                    val index = withContext(dispatcher) {
                        filteredList?.indexOfFirst { country -> country.phoneCode.toString() == defaultCountryCode } ?: 0
                    }

                    Resource.Success(
                        CountryListState(
                            countries = filteredList,
                            defaultSelectedPosition = index
                        )
                    )
                }
            }
        }
    }
}