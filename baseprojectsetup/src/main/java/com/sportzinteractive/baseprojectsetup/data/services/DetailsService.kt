package com.sportzinteractive.baseprojectsetup.data.services

import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.AssetContent
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.article.ArticleDataEntity
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.photos.PhotoDataEntity
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.videos.VideoDataEntity
import retrofit2.http.GET
import retrofit2.http.Url

interface DetailsService {

    @GET
    suspend fun getPhotoDetails(@Url url: String): BaseResponse<AssetContent<PhotoDataEntity>>


    @GET
    suspend fun getArticleDetails(@Url url: String): BaseResponse<AssetContent<ArticleDataEntity>>


    @GET
    suspend fun getVideoDetails(@Url url: String): BaseResponse<AssetContent<VideoDataEntity>>
}