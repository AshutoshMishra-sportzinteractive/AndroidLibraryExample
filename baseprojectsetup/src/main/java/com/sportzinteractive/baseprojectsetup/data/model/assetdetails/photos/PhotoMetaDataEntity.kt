package com.sportzinteractive.baseprojectsetup.data.model.assetdetails.photos


import com.google.gson.annotations.SerializedName

data class PhotoMetaDataEntity(
    @SerializedName("image_alt")
    val imageAlt: String?,
    @SerializedName("image_caption")
    val imageCaption: String?,
    @SerializedName("image_copyright")
    val imageCopyright: String?,
    @SerializedName("image_desc")
    val imageDesc: String?,
    @SerializedName("image_id")
    val imageId: String?,
    @SerializedName("imageName")
    val imageName: String?,
    @SerializedName("imagePath")
    val imagePath: String?,
    @SerializedName("is_active")
    val isActive: String?,
    @SerializedName("is_cover")
    val isCover: String?,
    @SerializedName("is_delete")
    val isDelete: String?,
    @SerializedName("order_number")
    val orderNumber: String?,
    @SerializedName("phototypeid")
    val phototypeid: Int?,
    @SerializedName("position_id")
    val positionId: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
)