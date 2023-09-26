package com.sportzinteractive.baseprojectsetup.data.model.footballsquad

import com.google.gson.annotations.SerializedName

data class Squads(
    @SerializedName("parent_series_id")
    val parentSeriesId: String,
    @SerializedName("series_id")
    val seriesId: String,
    @SerializedName("squad")
    val squad: Squad,
    @SerializedName("team_details")
    val teamDetails: TeamDetails
)