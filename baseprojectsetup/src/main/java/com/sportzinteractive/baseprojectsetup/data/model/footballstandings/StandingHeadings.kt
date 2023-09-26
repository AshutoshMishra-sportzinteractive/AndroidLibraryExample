package com.sportzinteractive.baseprojectsetup.data.model.footballstandings

import com.google.gson.annotations.SerializedName

data class StandingHeadings(
    @SerializedName("key")
    val key:String,
    @SerializedName("title")
    val title:String
)
