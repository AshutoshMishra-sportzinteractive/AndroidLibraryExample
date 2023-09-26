package com.sportzinteractive.baseprojectsetup.data.model.auth

import com.google.gson.annotations.SerializedName

data class SignOutRequest(
    @SerializedName("user_guid")
    val userGuid: String?,
    @SerializedName("is_app")
    val isApp: String?,
    @SerializedName("captcha")
    val captcha: String?,
)