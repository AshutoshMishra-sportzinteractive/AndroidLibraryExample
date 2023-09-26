package com.sportzinteractive.baseprojectsetup.data.model.notification

import com.google.gson.annotations.SerializedName

data class Pref(
    @SerializedName("asset_type")
    val assetType: Int,
    @SerializedName("event_id")
    val eventId: String,
    @SerializedName("lang_code")
    val langCode: String,
    @SerializedName("login_required")
    val loginRequired: Int,
    @SerializedName("tags")
    val tags: String
)