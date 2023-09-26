package com.sportzinteractive.baseprojectsetup.data.model.footballfixtures


import com.google.gson.annotations.SerializedName

data class PlayersInvolved(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: String?
)