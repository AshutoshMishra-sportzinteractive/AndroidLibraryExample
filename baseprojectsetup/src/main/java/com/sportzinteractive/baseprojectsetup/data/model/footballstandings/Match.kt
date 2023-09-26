package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("match_result")
    val matchResult: String?,
    @SerializedName("post_match_position")
    val postMatchPosition: String?,
    @SerializedName("prev_position")
    val prevPosition: String?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("teama_id")
    val teamaId: Int?,
    @SerializedName("teama_score")
    val teamaScore: Int?,
    @SerializedName("teama_short_name")
    val teamaShortName: String?,
    @SerializedName("teama_win_margin")
    val teamaWinMargin: Int?,
    @SerializedName("teamb_id")
    val teambId: Int?,
    @SerializedName("teamb_score")
    val teambScore: Int?,
    @SerializedName("teamb_short_name")
    val teambShortName: String?,
    @SerializedName("teamb_win_margin")
    val teambWinMargin: Int?
)