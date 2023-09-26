package com.sportzinteractive.baseprojectsetup.data.model.notification

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("clientid")
    val clientId: String,
    @SerializedName("devicetoken")
    val deviceToken: String,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("pref")
    val pref: List<Pref>,
    @SerializedName("user_guid")
    val userGuid: String
)
