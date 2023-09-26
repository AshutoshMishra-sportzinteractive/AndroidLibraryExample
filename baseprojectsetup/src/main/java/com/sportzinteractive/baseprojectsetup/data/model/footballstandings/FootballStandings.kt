package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class FootballStandings(
    @SerializedName("standings")
    val standings: Standings?
)