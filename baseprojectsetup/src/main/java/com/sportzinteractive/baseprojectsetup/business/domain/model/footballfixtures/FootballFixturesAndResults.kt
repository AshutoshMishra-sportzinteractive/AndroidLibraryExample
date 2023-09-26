package com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures

data class FootballFixturesAndResults(
    val toDaysMatchList:FootballMatchListDateWise?,
    val dateWiseMatchList:List<FootballMatchListDateWise>,
    val allUpcomingMatchLists:List<Matches>,
    val liveMatchPosition:Int?,
    val isGameState:Boolean? = false
)

data class FootballMatchListDateWise(
    val isLiveMatch:Boolean,
    val date:String,
    val matchDay:String,
    val list: List<Matches?>
)