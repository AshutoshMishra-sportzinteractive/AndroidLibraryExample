package com.sportzinteractive.baseprojectsetup.data.repository

import com.sportzinteractive.baseprojectsetup.data.model.country.Country
import com.sportzinteractive.baseprojectsetup.data.model.state.StateObject
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GeneralRepository {


    fun getStateList(url:String):Flow<Resource<List<StateObject?>>>

    fun getCountryList(url:String): Flow<Resource<List<Country>?>>



}