package com.sportzinteractive.baseprojectsetup.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val _loading by lazy {
        MutableLiveData(false)
    }

    val loading: LiveData<Boolean>
        get() = _loading

    protected fun setLoading(value: Boolean) {
        _loading.postValue(value)
    }

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = _message

    fun emitMessage(message: String) {
        _message.value = Event(message)
    }
}