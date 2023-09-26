package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class Standings(
    @SerializedName("groups")
    val groups: List<Group?>?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("parent_series_global_id")
    val parentSeriesGlobalId: Int?,
    @SerializedName("parent_series_id")
    val parentSeriesId: Int?,
    @SerializedName("series_id")
    val seriesId: Int?
)