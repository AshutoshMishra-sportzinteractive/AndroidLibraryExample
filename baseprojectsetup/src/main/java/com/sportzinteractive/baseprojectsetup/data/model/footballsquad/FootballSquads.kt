package com.sportzinteractive.baseprojectsetup.data.model.footballsquad

import com.google.gson.annotations.SerializedName

data class FootballSquads(
    @SerializedName("squads")
    val squads: Squads
)