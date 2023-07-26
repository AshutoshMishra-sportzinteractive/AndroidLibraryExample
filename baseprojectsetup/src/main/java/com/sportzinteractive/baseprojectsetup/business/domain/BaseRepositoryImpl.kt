package com.sportzinteractive.baseprojectsetup.business.domain

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sportzinteractive.baseprojectsetup.data.repository.BaseRepository
import com.sportzinteractive.baseprojectsetup.data.services.BaseService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.ApiResult
import com.sportzinteractive.baseprojectsetup.helper.NetworkThrowable
import com.sportzinteractive.baseprojectsetup.helper.Resource
import com.sportzinteractive.baseprojectsetup.helper.safeApiCall
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class BaseRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val authServices: BaseService
) : BaseRepository {


    override fun <V : Any> rawJsonBaseApiCall(
        requestBody: Any,
        url: String
    ): Flow<Resource<Response<V>>?> {
        return flow {
            val gson = Gson()
            val body = gson.toJson(requestBody)
            val jsonObject = gson.fromJson(body, JsonObject::class.java)
            val result = safeApiCall(dispatcher) {
                authServices.rawJsonBaseApiCall<V>(
                    url,
                    jsonObject
                )
            }
            when (result) {
                is ApiResult.GenericError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            result.code,
                            result.message ?: ""
                        )
                    )
                )
                is ApiResult.NetworkError -> emit(
                    Resource.Error(
                        NetworkThrowable(
                            0,
                            result.message ?: ""
                        )
                    )
                )
                is ApiResult.Success -> {
                    emit(Resource.Success(result.data))
                }
            }
        }
    }

}