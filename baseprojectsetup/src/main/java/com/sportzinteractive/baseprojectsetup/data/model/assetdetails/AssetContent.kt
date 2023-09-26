package com.sportzinteractive.baseprojectsetup.data.model.assetdetails

import com.google.gson.annotations.SerializedName

data class AssetContent<T>(
    @SerializedName("data")
    val data: T,
)