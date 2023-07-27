package com.sportzinteractive.baseprojectsetup.data.model.country

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("countries")
    val countries: List<Country>?
)

data class Country(
    @SerializedName("name")
    val name: String,
    @SerializedName("country_id")
    val countryId: Int?,
    @SerializedName("phone_code")
    val phoneCode: Int?,
    @SerializedName("short_name")
    val shortName: String?
){
    override fun toString(): String = name
}