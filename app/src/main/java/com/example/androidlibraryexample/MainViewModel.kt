package com.example.androidlibraryexample

import androidx.lifecycle.viewModelScope
import com.sportzinteractive.baseprojectsetup.business.interceptor.GetCountryList
import com.sportzinteractive.baseprojectsetup.business.interceptor.GetStateList
import com.sportzinteractive.baseprojectsetup.helper.Resource
import com.sportzinteractive.baseprojectsetup.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getCountryList: GetCountryList,
    val getStateList: GetStateList
):BaseViewModel() {


    fun getCountries(){
        viewModelScope.launch {
            val countryListState = getCountryList("").firstOrNull { it !is Resource.Loading }?.data
            println(countryListState?.countries)
        }
    }


}