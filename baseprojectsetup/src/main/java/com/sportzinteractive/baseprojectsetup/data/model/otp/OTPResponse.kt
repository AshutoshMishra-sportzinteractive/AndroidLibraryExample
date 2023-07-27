package com.sportzinteractive.baseprojectsetup.data.model.otp

import com.google.gson.annotations.SerializedName

data class OTPResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
)