package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.Component
import com.sportzinteractive.baseprojectsetup.business.domain.WidgetView
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsViewType
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.Module
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import javax.inject.Inject

class NewsModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper
):EntityMapper<List<Module>?, List<NewsListingItem>?> {
    override fun toDomain(entity: List<Module>?): List<NewsListingItem>? {
        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {

                NewsViewType.LISTING -> {
                    if (module?.widgetData?.items.isNullOrEmpty())
                        NewsListingItem.Unknown
                    else
                        NewsListingItem.Listing(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty(),
                        )
                }
                NewsViewType.UNKNOWN -> {
                    NewsListingItem.Unknown
                }
            }
        }.orEmpty()
    }


    private fun getWidgetType(componentName: String?, layoutType: String?): NewsViewType {
        return when {
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> NewsViewType.LISTING
            else -> NewsViewType.UNKNOWN
        }
    }
}