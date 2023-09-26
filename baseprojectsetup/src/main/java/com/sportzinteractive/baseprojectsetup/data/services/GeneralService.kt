package com.sportzinteractive.baseprojectsetup.data.services

import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.country.Countries
import com.sportzinteractive.baseprojectsetup.data.model.state.State
import retrofit2.http.GET
import retrofit2.http.Url

interface GeneralService {

    @GET
    suspend fun getCountryList(
        @Url url: String
    ): BaseResponse<Countries>

    @GET
    suspend fun getStateList(
        @Url url:String
    ): State


}