package com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings

data class Match(
    val date: String?,
    val id: Int?,
    val matchResult: String?,
    val postMatchPosition: String?,
    val prevPosition: String?,
    val result: String?,
    val teamAId: Int?,
    val teamAScore: Int?,
    val teamAShortName: String?,
    val teamAWinMargin: Int?,
    val teamBId: Int?,
    val teamBScore: Int?,
    val teamBShortName: String?,
    val teamBWinMargin: Int?,
    val teamALogo: String?,
    val teamBLogo: String?
) {
}