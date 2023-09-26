package com.sportzinteractive.baseprojectsetup.data.model.account

import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
)