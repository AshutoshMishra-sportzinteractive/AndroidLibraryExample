package com.sportzinteractive.baseprojectsetup.data.mapper.details


import com.sportzinteractive.baseprojectsetup.business.domain.model.details.PhotoDetails
import com.sportzinteractive.baseprojectsetup.business.domain.model.details.PhotoItem
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.photos.PhotoDataEntity
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoDataEntityMapper @Inject constructor(
    private val baseInfo: BaseInfo
) : EntityMapper<PhotoDataEntity, PhotoDetails> {

    override fun toDomain(entity: PhotoDataEntity): PhotoDetails {
        val entityDataPriority2 = entity.entityData?.find { it.priority == 2 }
        val categoryTag = entityDataPriority2?.entDispName ?: "Photo"
        val sharingUrl = baseInfo.getContentSharingUrl(
            entityCategory = entityDataPriority2?.canonical ?: "/photos",
            titleAlias = entity.slugUrl ?: ""
        )
        return PhotoDetails(
            title = entity.title,
            guid = entity.guid,
            albumId = entity.albumId,
            albumDesc = entity.albumDesc,
            albumItems = entity.photos?.map { image ->
                PhotoItem(
                    image_id = image.dataEntity?.imageId,
                    title = image.dataEntity?.title,
                    imageUrl = baseInfo.getContentImageUrl(
                        imagePath = image.dataEntity?.imagePath ?: "",
                        imageName = image.dataEntity?.imageName ?: ""
                    ),
                    caption = image.dataEntity?.imageCaption,
                    desc = image.dataEntity?.imageDesc,
                    isCover = image.dataEntity?.isCover == "1"
                )
            },
            publishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_DETAILS,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            categoryTag = categoryTag,
            sharingUrl = sharingUrl
        )
    }
}