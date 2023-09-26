package com.sportzinteractive.baseprojectsetup.data.model.footballfixtures


import com.google.gson.annotations.SerializedName

data class FootballFixturesAndResult(
    @SerializedName("matches")
    val matches: List<Matches?>,
    val clubId:String?,
    val nextCount:Int=0,
    val prevCount:Int=0,
    val sortOrder:Int=0
)