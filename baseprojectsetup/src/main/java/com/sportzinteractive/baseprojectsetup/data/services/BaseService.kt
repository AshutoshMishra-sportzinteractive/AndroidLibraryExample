package com.sportzinteractive.baseprojectsetup.data.services

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface BaseService {

    @POST
    suspend fun <V:Any> rawJsonBaseApiCall(
        @Url url: String,
        @Body inputModel: JsonObject
    ):Response<V>

    @GET
    suspend fun <V:Any> rawBaseApiCallGet(
        @Url url:String
    ):Response<V>

}