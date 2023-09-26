package com.sportzinteractive.baseprojectsetup.business.domain.model.details

import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import java.io.Serializable

data class VideoDetails(
    val title: String?,
    val guid: String?,
    val videoId: Int?,
    val desc: String?,
    val videoUrl: String?,
    val hlsUrl: String?,
    val imageName: String?,
    val imagePath: String?,
    val imageUrl: String?,
    val relatedVideo: List<AssetItem>?,
    val publishedDate: String?,
    val videoUrlWithToken: String? = null,
    val categoryTag: String?,
    val sharingUrl: String?,
    val duration: String,
    val contentSourceId: String?,
    val beautifiedDuration: String?, //--:-- or 02:55
) : Serializable

enum class VideoType(val contentSourceId: String) {
    BRIGHTCOVE("33"),
    YOUTUBE("1")
}
