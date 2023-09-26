package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class ApiConfig(
    @SerializedName("feedpath")
    val feedPath: String?
)
