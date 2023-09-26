package com.sportzinteractive.baseprojectsetup.data.model.auth

import com.google.gson.annotations.SerializedName

data class UpdateUserProfileRequest(
    @SerializedName("user")
    val user: UserEntity?,
    @SerializedName("imagejson")
    val imageJson: ImageJson?,
    @SerializedName("otp")
    val otp:String?,
    @SerializedName("email_id")
    val emailId:String?,
    @SerializedName("user_guid")
    val userGuid:String?,
    @SerializedName("captcha")
    val captcha:String?,
    @SerializedName("is_custom_image")
    val isCustomImage:String?,
    @SerializedName("is_app") val isApp: String? = "1",
)

data class ImageJson(
    @SerializedName("imageName")
    val imageName:String,
    @SerializedName("imageUrl")
    val imageUrl:String,
    @SerializedName("imageJson")
    val imageJson:String,
    @SerializedName("userguid")
    val userguid:String,

)
