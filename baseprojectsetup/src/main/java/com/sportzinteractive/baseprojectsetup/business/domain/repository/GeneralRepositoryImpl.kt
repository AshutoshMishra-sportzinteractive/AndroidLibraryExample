package com.sportzinteractive.baseprojectsetup.business.domain.repository

import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.country.Countries
import com.sportzinteractive.baseprojectsetup.data.model.country.Country
import com.sportzinteractive.baseprojectsetup.data.model.state.StateObject
import com.sportzinteractive.baseprojectsetup.data.repository.GeneralRepository
import com.sportzinteractive.baseprojectsetup.data.services.GeneralService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.*
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ViewModelScoped
class GeneralRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val generalService: GeneralService,
    private val baseInfo: BaseInfo
) : GeneralRepository {



    override fun getCountryList(url:String): Flow<Resource<List<Country>?>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(dispatcher) {
                generalService.getCountryList(url = url)
            }
            val resource =
                object : ApiResultHandler<BaseResponse<Countries>, List<Country>?>(result) {
                    override suspend fun handleSuccess(resultObj: BaseResponse<Countries>): Resource<List<Country>?> {
                        return Resource.Success(resultObj.content?.countries)
                    }
                }.getResult()

            emit(resource)
        }
    }

    override fun getStateList(url:String): Flow<Resource<List<StateObject?>>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(dispatcher) {
                generalService.getStateList(
                    url?:""
                )
            }
            when(result) {
                is ApiResult.GenericError -> emit(Resource.Error(NetworkThrowable(code = result.code, message = result.message.orEmpty())))
                is ApiResult.NetworkError -> emit(Resource.Error(NetworkThrowable(code = null, message = result.message.orEmpty())))
                is ApiResult.Success -> emit(Resource.Success(data = result.data?.content?.states))
            }
        }
    }


}