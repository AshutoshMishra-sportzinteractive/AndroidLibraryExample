package com.sportzinteractive.baseprojectsetup.business.domain.model.listing

data class BannerItem(
    val assetId: Int?,
    val bannerLink: String? = "",
    val bannerImageUrl: String? = "",
    val title: String? = "",
    val titleAlias: String? = "",
    val beautifiedDuration: String? = "",
    val height: Int? = null,
    val width: Int? = null,
    var reactCount: String? = "",
    val sharingUrl: String? = null,
    val assetType: AssetUtils.AssetType = AssetUtils.getAssetType(
        assetTypeId = -1
    ),
    val albumCount: Int? = null
){
    fun getIdWithAssetType():String{
        return assetId.toString() +"_"+assetType.typeId.toString()
    }
}
