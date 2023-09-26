package com.sportzinteractive.baseprojectsetup.business.domain.home

import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballMatchListDateWise
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.FootballStandingsData
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.BannerItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.Story
sealed class HomeListingItem(val type: HomeItemViewType, open val dataAvailable: Boolean = true) {
    data class Stories(val items: List<Story>) : HomeListingItem(type = HomeItemViewType.STORIES)

    data class Videos(val title: String, val moreTitle: String, val items: List<AssetItem>) :
        HomeListingItem(type = HomeItemViewType.VIDEOS)

    data class News(val title: String, val moreTitle: String, val items: List<AssetItem>) :
        HomeListingItem(type = HomeItemViewType.NEWS)

    data class Photos(val title: String, val moreTitle: String, val items: List<AssetItem>) :
        HomeListingItem(type = HomeItemViewType.PHOTOS)

    data class Fixtures(
        val title: String,
        val moreTitle: String,
        val feedType: String? = null,
        val feedValue: String? = null,
        val sortOrder: Int? = null,
        val nextCount: Int? = null,
        val prevCount: Int? = null,
        val toDaysMatchList: FootballMatchListDateWise?,
        val dateWiseMatchList: List<FootballMatchListDateWise>,
        val allUpcomingMatchLists: List<com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.Matches>,
        val moreLink: String? = null,
        override val dataAvailable: Boolean = false
    ) : HomeListingItem(type = HomeItemViewType.FIXTURES, dataAvailable = dataAvailable)

    data class Standings(
        val title: String,
        val moreTitle: String,
        val items: FootballStandingsData?,
        val headingData: Map<String, String>,
        val seriesId: Int?,
        override val dataAvailable: Boolean = false
    ) : HomeListingItem(type = HomeItemViewType.STANDINGS, dataAvailable = dataAvailable)

    data class Squad(
        val title: String,
        val items: List<Any>,
        override val dataAvailable: Boolean = false
    ) : HomeListingItem(type = HomeItemViewType.SQUAD, dataAvailable = dataAvailable)

    data class ShowCase(val title: String, val items: List<BannerItem>) :
        HomeListingItem(type = HomeItemViewType.SHOWCASE)

    object Unknown : HomeListingItem(type = HomeItemViewType.UNKNOWN)
}