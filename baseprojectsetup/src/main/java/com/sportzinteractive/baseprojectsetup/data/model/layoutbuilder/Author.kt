package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("asset_id")
    val assetId: Int?,
    @SerializedName("asset_type_id")
    val assetTypeId: Int?,
    @SerializedName("author_id")
    val authorId: Int?,
    @SerializedName("author_name")
    val authorName: String?,
    @SerializedName("content_provider_name")
    val contentProviderName: String?,
    @SerializedName("content_source_id")
    val contentSourceId: Int?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("user_name")
    val userName: String?
)