package com.sportzinteractive.baseprojectsetup.data.model.account


import com.google.gson.annotations.SerializedName
import com.sportzinteractive.baseprojectsetup.utils.DeviceName

data class SendAccountRequest(
    @SerializedName("is_app") val isApp: String? = "1",
    @SerializedName("captcha") val captcha: String = "",
    @SerializedName("platform_version") val platform_version: String?,
    @SerializedName("platform_type") val platform_type: Int? = 2,
    @SerializedName("device_name") val device_name: String? = DeviceName.getPhoneDeviceName()
)