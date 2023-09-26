package com.sportzinteractive.baseprojectsetup.data.model.notification

import com.google.gson.annotations.SerializedName

data class NotificationRequest(
    @SerializedName("data")
    val data: Data
)