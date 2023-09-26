package com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures

import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.PlayersInvolved

data class Participant(
    val highlight: String?,
    val id: String?,
    val name: String?,
    val playersInvolved: List<PlayersInvolved?>?,
    val value: String?,
    var teamLogo:String,
    val shortName: String?
)