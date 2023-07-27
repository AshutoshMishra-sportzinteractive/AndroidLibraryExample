package com.sportzinteractive.baseprojectsetup.business.interceptor

import com.sportzinteractive.baseprojectsetup.business.model.CountryListState
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.country.Countries
import com.sportzinteractive.baseprojectsetup.data.repository.BaseRepository
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
    private val baseRepository: BaseRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
){

    operator fun invoke(url:String,defaultCountryId:String="101"):Flow<Resource<CountryListState>>{
        return baseRepository.rawBaseApiCallGet<BaseResponse<Countries>>(url).map {
            when(it) {
                is Resource.Error -> Resource.Error(throwable = it.throwable)
                is Resource.Loading -> Resource.Loading()
                else -> {
                    val filteredList = it?.data?.body()?.content?.countries?.filter { e -> e.phoneCode!=0 }
                    val index = withContext(dispatcher) {
                        filteredList?.indexOfFirst { country -> country.countryId.toString() == defaultCountryId } ?: 0
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