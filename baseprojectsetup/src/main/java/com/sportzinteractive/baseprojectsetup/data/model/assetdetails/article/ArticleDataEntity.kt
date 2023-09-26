package com.sportzinteractive.baseprojectsetup.data.model.assetdetails.article

import com.google.gson.annotations.SerializedName
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.EntityData

data class ArticleDataEntity(
    @SerializedName("article_id")
    val articleId: String?,
    @SerializedName("asset_type")
    val assetType: String?,
    @SerializedName("asset_type_id")
    val assetTypeId: Int?,
    @SerializedName("full_text")
    val fullText: String?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("short_title")
    val shortTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_alias")
    val titleAlias: String?,
    @SerializedName("entitydata")
    val entityData: List<EntityData>?,
    @SerializedName("image_path")
    val imageFilePath: String?,
    @SerializedName("image_file_name")
    val imageFileName: String?,
    @SerializedName("intro_text")
    val introText: String?,
    @SerializedName("slug_url")
    val slugUrl: String?
)