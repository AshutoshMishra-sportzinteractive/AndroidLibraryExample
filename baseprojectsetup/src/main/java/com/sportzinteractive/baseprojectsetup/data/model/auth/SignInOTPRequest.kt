package com.sportzinteractive.baseprojectsetup.data.model.auth

import com.google.gson.annotations.SerializedName

data class SignInOtpRequest(
    @SerializedName("captcha")
    val captcha: String,
    @SerializedName("device_name")
    val deviceName: String,
    @SerializedName("is_app")
    val isApp: String,
    @SerializedName("mobile_no")
    val mobileNo: String,
    @SerializedName("country_code")
    val country_code: String,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("platform_type")
    val platform_type: Int? = 2,
    @SerializedName("platform_version")
    val platform_version: String? = "",
)