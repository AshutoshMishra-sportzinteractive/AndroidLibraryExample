package com.sportzinteractive.baseprojectsetup.data.repository

import com.sportzinteractive.baseprojectsetup.business.domain.model.details.ArticleDetail
import com.sportzinteractive.baseprojectsetup.business.domain.model.details.PhotoDetails
import com.sportzinteractive.baseprojectsetup.business.domain.model.details.VideoDetails
import com.sportzinteractive.baseprojectsetup.helper.Resource
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    fun getPhotoDetails(url: String): Flow<Resource<PhotoDetails?>>

    fun getArticleDetails(url: String): Flow<Resource<ArticleDetail?>>

    fun getVideoDetails(url: String): Flow<Resource<VideoDetails?>>
}