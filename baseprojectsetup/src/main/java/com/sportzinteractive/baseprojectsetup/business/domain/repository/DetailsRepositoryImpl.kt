package com.sportzinteractive.baseprojectsetup.business.domain.repository

import com.sportzinteractive.baseprojectsetup.business.domain.model.details.ArticleDetail
import com.sportzinteractive.baseprojectsetup.business.domain.model.details.PhotoDetails
import com.sportzinteractive.baseprojectsetup.business.domain.model.details.VideoDetails
import com.sportzinteractive.baseprojectsetup.data.mapper.details.ArticleDataEntityMapper
import com.sportzinteractive.baseprojectsetup.data.mapper.details.PhotoDataEntityMapper
import com.sportzinteractive.baseprojectsetup.data.mapper.details.VideoDataEntityMapper
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.AssetContent
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.article.ArticleDataEntity
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.photos.PhotoDataEntity
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.videos.VideoDataEntity
import com.sportzinteractive.baseprojectsetup.data.repository.DetailsRepository
import com.sportzinteractive.baseprojectsetup.data.services.DetailsService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.ApiResultHandler
import com.sportzinteractive.baseprojectsetup.helper.NetworkThrowable
import com.sportzinteractive.baseprojectsetup.helper.Resource
import com.sportzinteractive.baseprojectsetup.helper.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsService: DetailsService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val photoDataEntityMapper: PhotoDataEntityMapper,
    private val videoDataEntityMapper: VideoDataEntityMapper,
    private val articleDataEntityMapper: ArticleDataEntityMapper
) : DetailsRepository {

    override fun getPhotoDetails(url: String): Flow<Resource<PhotoDetails?>> {
        return flow {
            emit(Resource.Loading())

            val result = safeApiCall(ioDispatcher) {
                detailsService.getPhotoDetails(url)
            }
            val resource = object :
                ApiResultHandler<BaseResponse<AssetContent<PhotoDataEntity>>, PhotoDetails>(
                    result
                ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<AssetContent<PhotoDataEntity>>): Resource<PhotoDetails?> {
                    return if (resultObj.status == 200 && resultObj.content != null) {
                        Resource.Success(resultObj.content.data.let {
                            photoDataEntityMapper.toDomain(it)
                        })
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                resultObj.status, ""
                            )
                        )
                    }
                }
            }.getResult()
            emit(resource)
        }
    }


    override fun getArticleDetails(
        url: String
    ): Flow<Resource<ArticleDetail?>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(ioDispatcher) {
                detailsService.getArticleDetails(url = url)
            }

            val resource = object :
                ApiResultHandler<BaseResponse<AssetContent<ArticleDataEntity>>, ArticleDetail>(
                    result
                ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<AssetContent<ArticleDataEntity>>): Resource<ArticleDetail?> {
                    return if (resultObj.status == 200 && resultObj.content != null) {
                        Resource.Success(resultObj.content.data.let {
                            articleDataEntityMapper.toDomain(it)
                        })
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                resultObj.status, ""
                            )
                        )
                    }
                }
            }.getResult()
            emit(resource)
        }
    }


    override fun getVideoDetails(url: String): Flow<Resource<VideoDetails?>> {
        return flow {
            emit(Resource.Loading())

            val result = safeApiCall(ioDispatcher) {
                detailsService.getVideoDetails(url)
            }
            val resource = object :
                ApiResultHandler<BaseResponse<AssetContent<VideoDataEntity>>, VideoDetails>(
                    result
                ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<AssetContent<VideoDataEntity>>): Resource<VideoDetails?> {
                    return if (resultObj.status == 200 && resultObj.content != null) {
                        Resource.Success(resultObj.content.data.let {
                            videoDataEntityMapper.toDomain(it)
                        })
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                resultObj.status, ""
                            )
                        )
                    }
                }
            }.getResult()
            emit(resource)
        }
    }
}