package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("team")
    val team: List<Team?>?
)