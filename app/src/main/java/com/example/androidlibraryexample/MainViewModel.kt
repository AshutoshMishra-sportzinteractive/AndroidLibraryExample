package com.example.androidlibraryexample

import androidx.lifecycle.viewModelScope
import com.sportzinteractive.baseprojectsetup.business.interceptor.GetCountryList
import com.sportzinteractive.baseprojectsetup.business.interceptor.GetStateList
import com.sportzinteractive.baseprojectsetup.business.interceptor.SendOTP
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
    val getStateList: GetStateList,
    val sendOTP: SendOTP
):BaseViewModel() {


    fun getCountries(){
        viewModelScope.launch {
            _loading.value = true
            getCountryList("https://stg-rr.sportz.io/apiv3/getcountrystatecity").collectLatest {
                when(it){
                    is Resource.Error ->{
                        _loading.value = false
                    }
                    is Resource.Loading ->{
                        _loading.value = true
                    }
                    is Resource.Success ->{
                        _loading.value = false
                        //_countryListState.value = it.data
                    }
                }
            }
        }
    }

    fun sendOtp(){
        viewModelScope.launch {

        }
    }


}