package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.Component
import com.sportzinteractive.baseprojectsetup.business.domain.WidgetView
import com.sportzinteractive.baseprojectsetup.business.domain.home.HomeItemViewType
import com.sportzinteractive.baseprojectsetup.business.domain.home.HomeListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.ListingEntityData
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsViewType
import com.sportzinteractive.baseprojectsetup.business.domain.videos.VideosListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.videos.VideosViewType
import com.sportzinteractive.baseprojectsetup.business.domain.videos.VideosViewType.*
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.Module
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import kotlinx.coroutines.channels.ticker
import javax.inject.Inject

class VideoModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper

): EntityMapper<List<Module>?, List<VideosListingItem>?> {
    override fun toDomain(entity: List<Module>?): List<VideosListingItem>? {
        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {
                HIGHLIGHTS -> {
                    if (module?.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.HighLights(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                            entityData = ListingEntityData(
                                entities = module.metaInfo?.entities?:"",
                                excludeEntities = module.metaInfo?.exclent?:"",
                                otherEntities = module.metaInfo?.otherent?:""
                            ),
                            backgroundType = module.metaInfo?.backgroundType?:0,
                            moreText = module.metaInfo?.moreText?:"more"
                        )
                }
                TOP_MOMENTS -> {
                    VideosListingItem.TopMoments(
                        title = module.displayTitle.orEmpty(),
                        items = module.widgetData?.items?.map {
                            assetItemEntityMapper.toDomain(
                                entity = it,
                                imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                            )
                        }.orEmpty(),
                        entityData = ListingEntityData(
                            entities = module.metaInfo?.entities?:"",
                            excludeEntities = module.metaInfo?.exclent?:"",
                            otherEntities = module.metaInfo?.otherent?:""
                        ),
                        backgroundType = module.metaInfo?.backgroundType?:0,
                        moreText = module.metaInfo?.moreText?:"more"
                    )
                }
                TOP_GOALS -> {
                    VideosListingItem.TopGoals(
                        title = module.displayTitle.orEmpty(),
                        items = module.widgetData?.items?.map {
                            assetItemEntityMapper.toDomain(
                                entity = it,
                                imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                            )
                        }.orEmpty(),
                        entityData = ListingEntityData(
                            entities = module.metaInfo?.entities?:"",
                            excludeEntities = module.metaInfo?.exclent?:"",
                            otherEntities = module.metaInfo?.otherent?:""
                        ),
                        backgroundType = module.metaInfo?.backgroundType?:0,
                        moreText = module.metaInfo?.moreText?:"more"
                    )
                }
                INTERVIEWS -> {
                    VideosListingItem.Interviews(
                        title = module.displayTitle.orEmpty(),
                        items = module.widgetData?.items?.map {
                            assetItemEntityMapper.toDomain(
                                entity = it,
                                imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                            )
                        }.orEmpty(),
                        entityData = ListingEntityData(
                            entities = module.metaInfo?.entities?:"",
                            excludeEntities = module.metaInfo?.exclent?:"",
                            otherEntities = module.metaInfo?.otherent?:""
                        ),
                        backgroundType = module.metaInfo?.backgroundType?:0,
                        moreText = module.metaInfo?.moreText?:"more"
                    )
                }
                GOAL_OF_WEEK -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.GoalOfWeek(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = null
                                )
                            }.orEmpty(),
                            isCarousel = module.metaInfo?.isCarousel ?: false
                        )
                }
                GAMING_HUB -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        VideosListingItem.Unknown
                    else
                        VideosListingItem.GamingHub(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = null
                                )
                            }.orEmpty(),
                            isCarousel = module.metaInfo?.isCarousel?:false
                        )
                }
                UNKNOWN -> {
                    VideosListingItem.Unknown
                }
            }
        }.orEmpty()
    }

    private fun getWidgetType(componentName: String?, layoutType: String?): VideosViewType {
        return when {
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> HIGHLIGHTS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_02 -> TOP_MOMENTS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_03 -> TOP_GOALS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_04 -> INTERVIEWS
            componentName == Component.SI_FIXED_DYNAMIC_CONTENT_LISTING.componentName && layoutType == WidgetView.LAYOUT_V01 -> GOAL_OF_WEEK
            componentName == Component.SI_FIXED_DYNAMIC_CONTENT_LISTING.componentName && layoutType == WidgetView.LAYOUT_V02 -> GAMING_HUB
            else -> UNKNOWN
        }
    }
}