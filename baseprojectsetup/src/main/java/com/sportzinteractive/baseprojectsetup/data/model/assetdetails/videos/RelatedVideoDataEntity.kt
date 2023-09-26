package com.sportzinteractive.baseprojectsetup.data.model.assetdetails.videos


import com.google.gson.annotations.SerializedName
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.AssetItemEntity

data class RelatedVideoDataEntity(
    @SerializedName("items") val items: List<AssetItemEntity>
)