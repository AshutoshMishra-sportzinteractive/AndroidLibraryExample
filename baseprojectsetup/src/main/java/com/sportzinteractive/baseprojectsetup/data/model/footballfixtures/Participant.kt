package com.sportzinteractive.baseprojectsetup.data.model.footballfixtures


import com.google.gson.annotations.SerializedName

data class Participant(
    @SerializedName("highlight")
    val highlight: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("players_involved")
    val playersInvolved: List<PlayersInvolved?>?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("value")
    val value: String?,
    var teamLogo:String
)