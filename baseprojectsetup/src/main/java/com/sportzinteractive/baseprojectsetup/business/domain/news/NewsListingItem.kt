package com.sportzinteractive.baseprojectsetup.business.domain.news

import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem

sealed class NewsListingItem(type: NewsViewType) {
    open class Listing(open val title: String, open val items: List<AssetItem>):NewsListingItem(type = NewsViewType.LISTING)
    object Unknown: NewsListingItem(type = NewsViewType.UNKNOWN)
}