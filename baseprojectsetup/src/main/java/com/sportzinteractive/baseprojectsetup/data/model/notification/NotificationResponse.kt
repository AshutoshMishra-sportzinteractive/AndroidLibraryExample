package com.sportzinteractive.baseprojectsetup.data.model.notification

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    @SerializedName("ApplicationDomain")
    val applicationDomain: Any,
    @SerializedName("Authodata")
    val authoData: Any,
    @SerializedName("EntityData")
    val entityData: Any,
    @SerializedName("ImagesData")
    val imagesData: Any,
    @SerializedName("NextPrev")
    val nextPrev: Any,
    @SerializedName("RelatedArticle")
    val relatedArticle: Any,
    @SerializedName("asycncall")
    val asycnCall: Boolean,
    @SerializedName("content")
    val content: Content,
    @SerializedName("data")
    val data: Any,
    @SerializedName("fetchfromcache")
    val fetchFromCache: Boolean,
    @SerializedName("message")
    val message: Any,
    @SerializedName("meta")
    val meta: Any,
    @SerializedName("response_status")
    val responseStatus: Any,
    @SerializedName("status")
    val status: Int,
    @SerializedName("time")
    val time: Any
)