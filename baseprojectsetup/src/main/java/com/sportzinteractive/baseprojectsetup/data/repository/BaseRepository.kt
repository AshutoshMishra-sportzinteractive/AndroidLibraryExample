package com.sportzinteractive.baseprojectsetup.data.repository

import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BaseRepository {


    fun <V : Any> rawJsonBaseApiCall(requestBody:Any,url:String):Flow<Resource<Response<V>>?>

    fun <V : Any> rawBaseApiCallGet(url:String):Flow<Resource<Response<V>>?>


}