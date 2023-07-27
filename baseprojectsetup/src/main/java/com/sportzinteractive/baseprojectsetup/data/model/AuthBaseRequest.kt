package com.sportzinteractive.baseprojectsetup.data.model

import com.google.gson.annotations.SerializedName

data class AuthBaseRequest<T>(
    @SerializedName("data")
    val data: T?
)