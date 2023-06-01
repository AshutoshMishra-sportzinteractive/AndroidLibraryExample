package com.example.androidlibraryexample.business

import com.example.androidlibraryexample.data.ApiService
import com.example.androidlibraryexample.data.AuthRepository
import com.example.androidlibraryexample.data.UserItem
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.ApiResult
import com.sportzinteractive.baseprojectsetup.helper.NetworkThrowable
import com.sportzinteractive.baseprojectsetup.helper.Resource
import com.sportzinteractive.baseprojectsetup.helper.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val authService: ApiService
    ) : AuthRepository {


    override fun getFakeStuff(id: Int): Flow<Resource<UserItem>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(dispatcher){
                authService.getFakeStuff(id)
            }
            when (result) {
                is ApiResult.GenericError -> emit(Resource.Error(NetworkThrowable( result.code,result.message ?: "")))
                is ApiResult.NetworkError -> emit(Resource.Error(NetworkThrowable( 0,result.message ?: "")))
                is ApiResult.Success -> {
                    emit(Resource.Success(result.data?.body()))
                }
                }
        }
    }
}