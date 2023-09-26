package com.sportzinteractive.baseprojectsetup.data.model.footballsquad

import com.google.gson.annotations.SerializedName

data class TeamDetails(
    @SerializedName("short_name")
    val shortName: String,
    @SerializedName("team_id")
    val teamId: Int,
    @SerializedName("team_name")
    val teamName: String
)