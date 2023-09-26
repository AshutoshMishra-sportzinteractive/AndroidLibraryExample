package com.sportzinteractive.baseprojectsetup.data.repository

import com.sportzinteractive.baseprojectsetup.business.domain.home.HomeListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballFixturesAndResults
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.FootballStandingsData
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.photos.PhotoListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.videos.VideosListingItem
import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.PlayerItem
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow

interface ListingRepository {

    fun getHomePageListing(url: String): Flow<Resource<List<HomeListingItem>?>>

    fun getFootballStandingsData(url: String): Flow<Resource<FootballStandingsData?>>

    fun getVideoPageListing(url: String): Flow<Resource<List<VideosListingItem>?>>

    fun getNewsPageListing(url: String): Flow<Resource<List<NewsListingItem>?>>

    fun getPhotoPageListing(url: String): Flow<Resource<List<PhotoListingItem>?>>

    fun getEntityListing(
        url: String
    ): Flow<Resource<List<AssetItem>?>>

    fun getFootballSquadsData(url: String): Flow<Resource<List<PlayerItem>?>>

    fun getFootballFixturesAndResult(url: String,type:Int,clubId:String?=null,nextCount:Int=0,prevCount:Int=0,sortOrder:Int=0): Flow<Resource<FootballFixturesAndResults?>>

}