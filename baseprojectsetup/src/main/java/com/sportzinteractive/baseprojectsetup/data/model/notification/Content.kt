package com.sportzinteractive.baseprojectsetup.data.model.notification

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("Msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)