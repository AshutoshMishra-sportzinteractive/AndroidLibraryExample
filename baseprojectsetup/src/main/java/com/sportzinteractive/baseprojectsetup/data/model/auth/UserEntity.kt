package com.sportzinteractive.baseprojectsetup.data.model.auth

import com.google.gson.annotations.SerializedName


data class UserEntity(
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("mobile_no")
    val mobileNo: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("country_id")
    val countryId: String?,
    @SerializedName("country_name")
    val countryName: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("pincode")
    val pinCode: String?,
    @SerializedName("favourite_player_id")
    val favouritePlayerId: String?,
    @SerializedName("favourite_player_name")
    val favouritePlayerName: Any?,
    @SerializedName("favourite_club")
    val favouriteClub:String?,
    @SerializedName("jersey_name")
    val jerseyName: String?,
    @SerializedName("jersey_number")
    val jerseyNumber: Int?,
    @SerializedName("ext_info")
    val extInfo: ExtInfoEntity?,
    @SerializedName("profile_completion_percentage")
    val profileCompletionPercentage: String?,
    @SerializedName("social_user_image")
    val socialUserImage: String?,
    @SerializedName("social_user_name")
    val socialUserName: String?,
    @SerializedName("subscribe_for_email")
    val subscribeForEmail: Boolean?,
    @SerializedName("subscribe_for_watsapp")
    val subscribeForWhatsapp: Boolean?,
    @SerializedName("accept_tnc")
    val acceptTermsAndCondition: Boolean?,
    @SerializedName("state_name")
    val state: String?,
    @SerializedName("state_id")
    val stateId: String?

)