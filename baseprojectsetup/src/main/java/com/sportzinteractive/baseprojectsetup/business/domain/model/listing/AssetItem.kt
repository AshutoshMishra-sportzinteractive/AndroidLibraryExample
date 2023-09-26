package com.sportzinteractive.baseprojectsetup.business.domain.model.listing

import java.io.Serializable

data class AssetItem(
//    val featured: Boolean = false,
    val assetId: Int? = null,
    val beautifiedDuration: String? = null,
//    val duration: String,
    val assetGuid: String? = null,
//    val shortDesc: String?,
    val assetTitle: String? = null,
//    val shortTitle: String?,
    val titleAlias: String? = null,
//    val createdDate: String?,
//    val displayName: String?,
//    val totalAssets: String?,
    val assetTypeId: Int = 0,
    val publishedDate: String?,
//    val primaryEntityRoleMapId: Int,
    val secondaryEntityRoleMapId: Int = 0,
    val imageUrl: String? = null,
//    val displayPublishedDate: String?,
//    val isLiked: Boolean = false,
    val sharingUrl: String? = null,
    val totalAssets: String? = null,
    var totalReacts: String? = null,
    val description: String? = null,
    val offer: String? = null,
    val price: String? = null,
    val discountPrice: String? = null,
    val externalLink: String? = null,
    val contentSourceId: String? = null,
    val bannerLink:String?=null,
    val bannerImage:String?=null,
    val webViewUrl:String?=null,
    val isWebView:Boolean?=false,
    val inAppBrowser:Boolean?=false,
    val isExternalWebView:Boolean?=false,
    val isWebAuth:Boolean?=false


) : Serializable {
    val assetType: AssetUtils.AssetType =
        AssetUtils.getAssetType(assetTypeId)

    fun getIdWithAssetType():String{
        return assetId.toString() + "_" + assetType.typeId.toString()
    }
}