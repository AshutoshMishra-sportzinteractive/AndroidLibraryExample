package com.sportzinteractive.baseprojectsetup.business.domain.photos

import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.ListingEntityData
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsViewType

sealed class PhotoListingItem(val type: PhotosViewType) {
    open class Listing(open val title: String, open val items: List<AssetItem>):PhotoListingItem(PhotosViewType.LISTING)
    object Unknown: PhotoListingItem(type = PhotosViewType.UNKNOWN)
}