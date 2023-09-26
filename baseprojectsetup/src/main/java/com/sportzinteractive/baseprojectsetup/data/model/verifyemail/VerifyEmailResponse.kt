package com.sportzinteractive.baseprojectsetup.data.model.verifyemail

import com.google.gson.annotations.SerializedName

data class VerifyEmailResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
)