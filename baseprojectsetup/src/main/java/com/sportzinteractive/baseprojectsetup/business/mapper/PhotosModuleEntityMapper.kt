package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.Component
import com.sportzinteractive.baseprojectsetup.business.domain.WidgetView
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.news.NewsViewType
import com.sportzinteractive.baseprojectsetup.business.domain.photos.PhotoListingItem
import com.sportzinteractive.baseprojectsetup.business.domain.photos.PhotosViewType
import com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder.Module
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import javax.inject.Inject

class PhotosModuleEntityMapper @Inject constructor(
    private val assetItemEntityMapper: AssetItemEntityMapper
) : EntityMapper<List<Module>?, List<PhotoListingItem>?>  {

    override fun toDomain(entity: List<Module>?): List<PhotoListingItem>? {
        return entity?.map { module ->
            val widgetType = getWidgetType(
                componentName = module.metaInfo?.component,
                layoutType = module.metaInfo?.view
            )
            return@map when (widgetType) {

                PhotosViewType.LISTING -> {
                    if (module?.widgetData?.items.isNullOrEmpty())
                        PhotoListingItem.Unknown
                    else
                        PhotoListingItem.Listing(
                            title = module.displayTitle.orEmpty(),
                            items = module.widgetData?.items?.map {
                                assetItemEntityMapper.toDomain(
                                    entity = it,
                                    imageRatio = module.metaInfo?.layoutData?.firstOrNull()?.imgRatio
                                )
                            }.orEmpty()
                        )
                }
                PhotosViewType.UNKNOWN -> {
                    PhotoListingItem.Unknown
                }
            }
        }.orEmpty()
    }


    private fun getWidgetType(componentName: String?, layoutType: String?): PhotosViewType {
        return when {
            componentName == Component.SI_LISTING.componentName && layoutType == WidgetView.LAYOUT_01 -> PhotosViewType.LISTING
            else -> PhotosViewType.UNKNOWN
        }
    }
}