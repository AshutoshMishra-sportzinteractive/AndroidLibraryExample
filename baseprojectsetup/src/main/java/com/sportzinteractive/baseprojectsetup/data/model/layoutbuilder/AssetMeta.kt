package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder


import com.google.gson.annotations.SerializedName

data class AssetMeta(
    @SerializedName("asset_id")
    val assetId: Int?,
    @SerializedName("content_source_id")
    val contentSourceId: Any?,
    @SerializedName("content_type")
    val contentType: Int?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("image_id")
    val imageId: Any?,
    @SerializedName("image_name")
    val imageName: String?,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("mob_image_id")
    val mobImageId: Any?,
    @SerializedName("mob_image_name")
    val mobImageName: Any?,
    @SerializedName("mob_image_path")
    val mobImagePath: Any?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_alias")
    val titleAlias: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("show_content")
    val showContent: Boolean?
)