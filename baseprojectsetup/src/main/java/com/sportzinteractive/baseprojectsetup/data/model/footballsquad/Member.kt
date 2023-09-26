package com.sportzinteractive.baseprojectsetup.data.model.footballsquad

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("country_id")
    val countryId: Int,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("player_id")
    val playerId: Int,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("role_name")
    val roleName: String,
    @SerializedName("short_name")
    val shortName: String
)