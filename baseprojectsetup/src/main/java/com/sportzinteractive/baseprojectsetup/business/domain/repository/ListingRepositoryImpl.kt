package com.sportzinteractive.baseprojectsetup.business.domain.repository

import com.sportzinteractive.baseprojectsetup.business.domain.FootballFixtureRequestType
import com.sportzinteractive.baseprojectsetup.business.domain.home.HomeListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballFixturesAndResults
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.FootballStandingsData
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.photos.PhotoListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.videos.VideosListingItem
import com.sportzinteractive.baseprojectsetup.business.mapper.*
import com.sportzinteractive.baseprojectsetup.data.model.BaseResponse
import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.FootballSquads
import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.FootballFixturesAndResult
import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.PlayerItem
import com.sportzinteractive.baseprojectsetup.data.model.footballstandings.FootballStandings
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.Content
import com.sportzinteractive.baseprojectsetup.data.repository.ListingRepository
import com.sportzinteractive.baseprojectsetup.data.services.ListingService
import com.sportzinteractive.baseprojectsetup.di.IoDispatcher
import com.sportzinteractive.baseprojectsetup.helper.ApiResultHandler
import com.sportzinteractive.baseprojectsetup.helper.NetworkThrowable
import com.sportzinteractive.baseprojectsetup.helper.Resource
import com.sportzinteractive.baseprojectsetup.helper.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.annotation.meta.When
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(
    private val listingService: ListingService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val moduleEntityMapper: HomeModuleEntityMapper,
    private val newsModuleEntityMapper: NewsModuleEntityMapper,
    private val photosModuleEntityMapper: PhotosModuleEntityMapper,
    private val videoModuleEntityMapper: VideoModuleEntityMapper,
    private val footballStandingsMapper: FootballStandingsMapper,
    private val assetItemEntityMapper: AssetItemEntityMapper,
    private val footballFixturesAndResultMapperForHome: FootballFixturesAndResultMapperForHome,
    private val footballFixturesAndResultMapperForListing: FootballFixturesAndResultMapperForListing,
    private val squadEntityMapper: SquadEntityMapper
) : ListingRepository {

    override fun getHomePageListing(url: String): Flow<Resource<List<HomeListingItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                listingService.getHomePageListing(url = url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<HomeListingItem>>(
                response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<HomeListingItem>?> {
                    val module = resultObj.content?.module?.filter { it.showInApp == 1 }
                        ?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }

                    return Resource.Success(data = moduleEntityMapper.toDomain(module)
                        ?.filter { it !is HomeListingItem.Unknown })

                }
            }.getResult()

            emit(resource)
        }
    }

    override fun getFootballStandingsData(url: String): Flow<Resource<FootballStandingsData?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                listingService.getFootballStandingsData(url = url)
            }
            val resource =
                object : ApiResultHandler<FootballStandings, FootballStandingsData?>(response) {
                    override suspend fun handleSuccess(resultObj: FootballStandings): Resource<FootballStandingsData?> {
                        val data = footballStandingsMapper.toDomain(resultObj)
                        return Resource.Success(data)
                    }

                }.getResult()
            emit(resource)
        }
    }

    override fun getVideoPageListing(url: String): Flow<Resource<List<VideosListingItem>?>> {
        return flow {
            emit(Resource.Loading())

            val result = safeApiCall(dispatcher = ioDispatcher) {
                listingService.getVideosPageListing(url)
            }

            val resource =
                object : ApiResultHandler<BaseResponse<Content>, List<VideosListingItem>>(
                    result
                ) {
                    override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<VideosListingItem>?> {
                        val module = resultObj.content?.module?.filter { it.showInApp == 1 }
                            ?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }

                        return Resource.Success(data = videoModuleEntityMapper.toDomain(module)
                            ?.filter { it !is VideosListingItem.Unknown })

                    }
                }.getResult()
            emit(Resource.Success(resource.data))

        }
    }

    override fun getNewsPageListing(url: String): Flow<Resource<List<NewsListingItem>?>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(ioDispatcher) {
                listingService.getNewsPageListing(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<NewsListingItem>>(
                result
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<NewsListingItem>?> {
                    val module = resultObj.content?.module?.filter { it.showInApp == 1 }
                        ?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }

                    return Resource.Success(data = newsModuleEntityMapper.toDomain(module)
                        ?.filter { it !is NewsListingItem.Unknown })

                }
            }.getResult()
            emit(Resource.Success(resource.data))
        }

    }

    override fun getPhotoPageListing(url: String): Flow<Resource<List<PhotoListingItem>?>> {
        return flow {
            emit(Resource.Loading())
            val result = safeApiCall(ioDispatcher) {
                listingService.getPhotosPageListing(url)
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<PhotoListingItem>>(
                result
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<PhotoListingItem>?> {
                    val module = resultObj.content?.module?.filter { it.showInApp == 1 }
                        ?.sortedBy { it.metaInfo?.widgetOrder ?: 0 }

                    return Resource.Success(data = photosModuleEntityMapper.toDomain(module)
                        ?.filter { it !is PhotoListingItem.Unknown })

                }
            }.getResult()
            emit(Resource.Success(resource.data))

        }
    }

    override fun getEntityListing(
        url: String
    ): Flow<Resource<List<AssetItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                listingService.getAssetsListing(
                    url = url
                )
            }

            val resource = object : ApiResultHandler<BaseResponse<Content>, List<AssetItem>>(
                response
            ) {
                override suspend fun handleSuccess(resultObj: BaseResponse<Content>): Resource<List<AssetItem>?> {
                    return if (resultObj.status == 200) {
                        Resource.Success(resultObj.content?.assetItemEntities?.map {
                            assetItemEntityMapper.toDomain(it)
                        })
                    } else {
                        Resource.Error(
                            NetworkThrowable(
                                code = null, message = "Failed to load!!"
                            )
                        )
                    }
                }
            }.getResult()

            emit(resource)
        }

    }

    override fun getFootballSquadsData(url: String): Flow<Resource<List<PlayerItem>?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                listingService.getFootballSquadList(
                    url = url
                )
            }

            val resource = object : ApiResultHandler<FootballSquads, List<PlayerItem>>(response){
                override suspend fun handleSuccess(resultObj: FootballSquads): Resource<List<PlayerItem>?> {
                    return Resource.Success(resultObj.squads.squad.players.map { squadEntityMapper.toDomain(it) })
                }

            }.getResult()
            emit(resource)
        }

    }

    override fun getFootballFixturesAndResult(url: String,type:Int,clubId:String?,nextCount:Int,prevCount:Int,sortOrder:Int): Flow<Resource<FootballFixturesAndResults?>> {
        return flow {
            emit(Resource.Loading())

            val response = safeApiCall(ioDispatcher) {
                listingService.getFootballFixtures(url = url)
            }

            val resource = object : ApiResultHandler<FootballFixturesAndResult, FootballFixturesAndResults>(response) {
                override suspend fun handleSuccess(resultObj: FootballFixturesAndResult): Resource<FootballFixturesAndResults?> {
                    val matchItems = when(type){
                        FootballFixtureRequestType.HOME.type -> footballFixturesAndResultMapperForHome.toDomain(resultObj.copy(nextCount = nextCount, prevCount = prevCount, sortOrder = sortOrder))
                        else -> footballFixturesAndResultMapperForListing.toDomain(resultObj.copy(clubId = clubId))
                    }
                    return Resource.Success(data = matchItems)
                }
            }.getResult()

            emit(resource)
        }
    }
}