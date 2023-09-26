package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class LayoutData(
    @SerializedName("aspect_ratio")
    val aspectRatio: String?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("imgRatio")
    val imgRatio: String?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("show_image")
    val showImage: Boolean?,
    @SerializedName("show_image_count")
    val showImageCount: Boolean?
)