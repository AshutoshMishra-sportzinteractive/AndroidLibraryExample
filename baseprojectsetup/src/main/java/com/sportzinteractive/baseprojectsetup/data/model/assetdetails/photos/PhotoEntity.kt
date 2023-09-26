package com.sportzinteractive.baseprojectsetup.data.model.assetdetails.photos

import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    @SerializedName("data")
    val dataEntity: PhotoMetaDataEntity?,
)