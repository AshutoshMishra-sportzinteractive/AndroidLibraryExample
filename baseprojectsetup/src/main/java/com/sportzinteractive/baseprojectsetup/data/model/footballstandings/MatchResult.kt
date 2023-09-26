package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class MatchResult(
    @SerializedName("match")
    val match: List<Match?>?
)