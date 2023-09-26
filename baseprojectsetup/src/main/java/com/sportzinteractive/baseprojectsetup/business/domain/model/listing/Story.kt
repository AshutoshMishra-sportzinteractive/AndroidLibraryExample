package com.sportzinteractive.baseprojectsetup.business.domain.model.listing

import java.io.Serializable

data class Story(
    val assetId: Int?,
    val assetType: Int?,
    val title: String?,
    val desc: String?,
    val imagePath: String?,
    val imageName: String?,
    val imageUrl: String?,
    val imageUrl_1_1: String?,
    val titleAlias: String?,
    val publishedData: String?,
    val authorName: String?,
    val authorId: Int?,
    val contentSourceId: String?,
    val contentProviderName: String?,
    val assetOrder: Int?,
    val sharingUrl: String? = null,
    var isStoryReacted : Boolean = false
) : Serializable{
    constructor(titleAlias: String?) : this(
            assetId=null,
            assetType = null,
            title = null,
            desc = null,
            imagePath = null,
            imageName = null,
            imageUrl = null,
            imageUrl_1_1 = null,
            titleAlias = titleAlias,
            publishedData = null,
            authorName = null,
            authorId = null,
            contentSourceId = null,
            contentProviderName = null,
            assetOrder = null,
            sharingUrl = null,)
}