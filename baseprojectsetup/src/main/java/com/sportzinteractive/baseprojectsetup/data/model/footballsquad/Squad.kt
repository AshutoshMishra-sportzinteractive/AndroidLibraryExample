package com.sportzinteractive.baseprojectsetup.data.model.footballsquad

import com.google.gson.annotations.SerializedName

data class Squad(
    @SerializedName("players")
    val players: List<Player>,
    @SerializedName("staff_details")
    val staffDetails: StaffDetails
)