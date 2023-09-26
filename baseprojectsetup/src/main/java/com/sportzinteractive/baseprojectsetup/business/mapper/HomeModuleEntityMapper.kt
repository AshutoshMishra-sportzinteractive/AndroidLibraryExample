package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.Component
import com.sportzinteractive.baseprojectsetup.business.domain.WidgetView
import com.sportzinteractive.baseprojectsetup.business.domain.home.HomeItemViewType
import com.sportzinteractive.baseprojectsetup.business.domain.home.HomeListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.FootballStandingsData
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.AssetUtils
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.BannerItem
import com.sportzinteractive.baseprojectsetup.business.domain.model.listing.Story
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.footballstandings.StandingHeadings
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.Module
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import javax.inject.Inject

class HomeModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper,
    private val baseInfo: BaseInfo
) :
    EntityMapper<List<Module>?, List<HomeListingItem>?> {

    override fun toDomain(entity: List<Module>?): List<HomeListingItem>? {

        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {

                HomeItemViewType.VIDEOS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        HomeListingItem.Unknown
                    else
                        HomeListingItem.Videos(
                            title = module.displayTitle.orEmpty(),
                            moreTitle = module.metaInfo?.moreText.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty()
                        )
                }


                HomeItemViewType.NEWS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        HomeListingItem.Unknown
                    else
                        HomeListingItem.News(
                            title = module.displayTitle.orEmpty(),
                            moreTitle = module.metaInfo?.moreText.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty()
                        )
                }


                HomeItemViewType.PHOTOS -> {
                    if (module.widgetData?.items.isNullOrEmpty())
                        HomeListingItem.Unknown
                    else
                        HomeListingItem.Photos(
                            title = module.displayTitle.orEmpty(),
                            moreTitle = module.metaInfo?.moreText.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty()
                        )
                }

                HomeItemViewType.STORIES -> {
                    if (module.widgetData?.data?.assetMap.isNullOrEmpty())
                        HomeListingItem.Unknown
                    else
                        HomeListingItem.Stories(
                            module.widgetData?.data?.assetMap?.map { assetMap ->
                                Story(
                                    assetId = assetMap.assetId,
                                    assetType = assetMap.assetType,
                                    title = assetMap.assetMeta?.title,
                                    desc = assetMap.assetMeta?.desc,
                                    imagePath = assetMap.assetMeta?.imagePath,
                                    imageName = assetMap.assetMeta?.imageName,
                                    imageUrl = baseInfo.getContentImageUrl(
                                        imagePath = assetMap.assetMeta?.imagePath ?: "",
                                        imageName = assetMap.assetMeta?.imageName ?: ""
                                    ),
                                    imageUrl_1_1 = baseInfo.getContentImageUrl(
                                        imagePath = assetMap.assetMeta?.imagePath ?: "",
                                        imageName = assetMap.assetMeta?.imageName ?: "",
                                        imageRatio = "1-1"
                                    ),
                                    titleAlias = assetMap.assetMeta?.titleAlias,
                                    publishedData = assetMap.publishDate,
                                    authorName = assetMap.author?.firstOrNull()?.authorName,
                                    authorId = assetMap.author?.firstOrNull()?.authorId,
                                    contentSourceId = assetMap.assetMeta?.contentSourceId?.toString(),
                                    contentProviderName = assetMap.author?.firstOrNull()?.contentProviderName,
                                    assetOrder = assetMap.assetOrder
                                )
                            } ?: listOf()
                        )
                }
                HomeItemViewType.STANDINGS -> {

                    HomeListingItem.Standings(
                        title = module.displayTitle.orEmpty(),
                        moreTitle = module.metaInfo?.moreText.orEmpty(),
                        items = FootballStandingsData(listOf()),
                        seriesId = module.metaInfo?.seriesId,
                        headingData = convertListIntoMap(module.metaInfo?.columnConfig ?: listOf())
                    )
                }
                HomeItemViewType.SQUAD -> {

                    HomeListingItem.Squad(
                        title = module.displayTitle.orEmpty(),
                        items = emptyList()
                    )
                }

                HomeItemViewType.SHOWCASE -> {
                    if (module.widgetData?.data?.assetMap.isNullOrEmpty())
                        HomeListingItem.Unknown
                    else
                        HomeListingItem.ShowCase(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.data?.assetMap?.map { assetMap ->
                                val entityDataPriority1 =
                                    assetMap.entitydata?.find { it.priority == 1 }
                                val entityDataPriority2 =
                                    assetMap.entitydata?.find { it.priority == 2 }
                                val sharingUrl = baseInfo.getContentSharingUrl(
                                    entityCategory = entityDataPriority2?.canonical,
                                    titleAlias = assetMap.assetMeta?.titleAlias.orEmpty()
                                )
                                BannerItem(
                                    assetId = assetMap.assetId,
                                    title = assetMap.assetMeta?.title,
                                    bannerImageUrl = baseInfo.getContentImageUrl(
                                        imagePath = assetMap.assetMeta?.imagePath ?: "",
                                        imageName = assetMap.assetMeta?.imageName ?: "",
                                        imageRatio = "1-1"
                                    ),
                                    titleAlias = assetMap.assetMeta?.titleAlias,
                                    beautifiedDuration = CalendarUtils.convertDateStringToSpecifiedDateString(
                                        dateString = assetMap.publishDate,
                                        dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST,
                                        requiredDateFormat = "dd MMM, YYYY"
                                    ),
                                    reactCount = null,
                                    assetType = AssetUtils.getAssetType(
                                        assetTypeId = assetMap.assetType
                                    ),
                                    sharingUrl = sharingUrl
                                )
                            }.orEmpty()
                        )
                }

                HomeItemViewType.FIXTURES -> HomeListingItem.Fixtures(
                    title = module.displayTitle.orEmpty(),
                    moreTitle = module.metaInfo?.moreText.orEmpty(),
                    moreLink = module.metaInfo?.moreLinks?.morePath,
                    feedType = module.metaInfo?.feedInfo?.feedType,
                    feedValue = module.metaInfo?.feedInfo?.feedValue,
                    sortOrder = module.metaInfo?.sortOrder,
                    nextCount = module.metaInfo?.matchCounts?.byDate?.next,
                    prevCount = module.metaInfo?.matchCounts?.byDate?.prev,
                    dateWiseMatchList = emptyList(),
                    toDaysMatchList = null,
                    allUpcomingMatchLists = emptyList()
                )

                else -> HomeListingItem.Unknown
            }

        }.orEmpty()

    }

    private fun getWidgetType(componentName: String?, layoutType: String?): HomeItemViewType {
        return when {
            componentName == Component.SI_SCORESTRIP.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.FIXTURES
            componentName == Component.SI_STORIES.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.STORIES
            componentName == Component.SI_SHOWCASE.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.SHOWCASE
            componentName == Component.SI_STANDINGS.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.STANDINGS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> HomeItemViewType.VIDEOS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_02 -> HomeItemViewType.NEWS
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_03 -> HomeItemViewType.PHOTOS
            else -> HomeItemViewType.UNKNOWN
        }
    }

    private fun convertListIntoMap(list:List<StandingHeadings>):Map<String,String>{
        val map = mutableMapOf<String,String>()
        list.forEach {
            map[it.key] = it.title
        }
        return map
    }

}