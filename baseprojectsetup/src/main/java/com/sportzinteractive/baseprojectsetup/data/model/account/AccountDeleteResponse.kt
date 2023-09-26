package com.sportzinteractive.baseprojectsetup.data.model.account

import com.google.gson.annotations.SerializedName

data class AccountDeleteResponse(
    @SerializedName("content")
    val content: Content?,
    @SerializedName("data")
    val data: AccountResponse?,
    @SerializedName("status")
    val status: Int?
)