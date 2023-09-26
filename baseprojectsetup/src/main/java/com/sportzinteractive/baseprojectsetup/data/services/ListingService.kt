package com.sportzinteractive.baseprojectsetup.data.services

import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.FootballSquads
import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.FootballFixturesAndResult
import com.sportzinteractive.baseprojectsetup.data.model.footballstandings.FootballStandings
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.Content
import retrofit2.http.GET
import retrofit2.http.Url

interface ListingService {

    @GET
    suspend fun getHomePageListing(@Url url: String): BaseResponse<Content>

    @GET
    suspend fun getNewsPageListing(@Url url: String): BaseResponse<Content>

    @GET
    suspend fun getPhotosPageListing(@Url url: String): BaseResponse<Content>

    @GET
    suspend fun getVideosPageListing(@Url url: String): BaseResponse<Content>

    @GET
    suspend fun getAssetsListing(@Url url: String): BaseResponse<Content>

    @GET
    suspend fun getFootballStandingsData(@Url url: String): FootballStandings

    @GET
    suspend fun getFootballSquadList(@Url url: String): FootballSquads

    @GET
    suspend fun getFootballFixtures(@Url url: String): FootballFixturesAndResult

}