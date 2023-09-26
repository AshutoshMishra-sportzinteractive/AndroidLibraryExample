package com.sportzinteractive.baseprojectsetup.data.model.footballsquad

import com.google.gson.annotations.SerializedName

data class StaffDetails(
    @SerializedName("member")
    val member: List<Member>
)