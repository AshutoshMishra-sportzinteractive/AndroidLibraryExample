package com.sportzinteractive.baseprojectsetup.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponseData<T>(
    @SerializedName("data")
    val data: T?,
    @SerializedName("status")
    val status: Int?
)