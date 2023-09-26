package com.sportzinteractive.baseprojectsetup.data.model.footballstandings


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("teams")
    val teams: Teams?
)