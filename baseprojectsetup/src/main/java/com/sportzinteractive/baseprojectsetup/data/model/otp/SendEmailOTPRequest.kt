package com.sportzinteractive.baseprojectsetup.data.model.otp

import com.google.gson.annotations.SerializedName
import com.sportzinteractive.baseprojectsetup.constants.CustomValues
import com.sportzinteractive.baseprojectsetup.utils.DeviceName

data class SendEmailOTPRequest(
    @SerializedName("email_id")
    val emailId: String?,
    @SerializedName("mobile_no")
    val mobile_no: String?,
    @SerializedName("otp")
    val otp: String? = null,
    @SerializedName("is_app")
    val isApp: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("type")
    val type: Int,
    @SerializedName("captcha")
    val captcha: String,
    @SerializedName("platform_version")
    val platform_version: String? = "",
    @SerializedName("platform_type")
    val platform_type: Int? = 2,
    @SerializedName("device_name")
    val device_name: String? = DeviceName.getPhoneDeviceName()
)