package com.sportzinteractive.baseprojectsetup.business.domain.videos

import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.ListingEntityData

sealed class VideosListingItem(val type:VideosViewType) {
    open class HighLights(open val title: String, open val items: List<AssetItem>, open val entityData: ListingEntityData,open val backgroundType:Int,open val moreText:String):VideosListingItem(VideosViewType.HIGHLIGHTS)
    open class TopMoments(open val title: String,open val items: List<AssetItem>, open val entityData: ListingEntityData,open val backgroundType:Int,open val moreText:String):VideosListingItem(VideosViewType.TOP_MOMENTS)
    open class TopGoals(open val title: String,open val items: List<AssetItem>, open val entityData: ListingEntityData,open val backgroundType:Int,open val moreText:String):VideosListingItem(VideosViewType.TOP_GOALS)
    open class Interviews(open val title: String,open val items: List<AssetItem>, open val entityData: ListingEntityData,open val backgroundType:Int,open val moreText:String):VideosListingItem(VideosViewType.INTERVIEWS)
    data class GoalOfWeek(open val title: String,val items: List<AssetItem>,val isCarousel:Boolean) :
        VideosListingItem(type = VideosViewType.GOAL_OF_WEEK)
    data class GamingHub(open val title: String,val items: List<AssetItem>,val isCarousel:Boolean) :
        VideosListingItem(type = VideosViewType.GAMING_HUB)

    object Unknown: VideosListingItem(type = VideosViewType.UNKNOWN)
}