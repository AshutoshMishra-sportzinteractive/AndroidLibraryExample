package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("items")
    val assetItemEntities: List<AssetItemEntity>,
    @SerializedName("module")
    val module: List<Module>?,
)