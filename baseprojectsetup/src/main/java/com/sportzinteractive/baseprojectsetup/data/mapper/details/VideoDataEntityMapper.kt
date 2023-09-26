package com.sportzinteractive.baseprojectsetup.data.mapper.details


import com.sportzinteractive.baseprojectsetup.business.domain.model.details.VideoDetails
import com.sportzinteractive.baseprojectsetup.business.mapper.AssetItemEntityMapper
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.videos.VideoDataEntity
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoDataEntityMapper @Inject constructor(
    private val baseInfo: BaseInfo,
    private val assetItemEntityMapper: AssetItemEntityMapper,
) : EntityMapper<VideoDataEntity, VideoDetails> {
    override fun toDomain(entity: VideoDataEntity): VideoDetails {
        val entityDataPriority2 = entity.entityData?.find { it.priority == 2 }

        val categoryTag = entityDataPriority2?.entDispName ?: "Video"
        val sharingUrl = baseInfo.getContentSharingUrl(
            entityCategory = entityDataPriority2?.canonical ?: "/videos",
            titleAlias = entity.slugUrl ?: ""
        )

        return VideoDetails(
            title = entity.title,
            guid = entity.guid,
            videoId = entity.videoId,
            desc = entity.desc,
            videoUrl = entity.videoUrl,
            hlsUrl = entity.hlsUrl,
            imageName = entity.imageFileName,
            imagePath = entity.imagePath,
            imageUrl = baseInfo.getContentImageUrl(
                imagePath = entity.imagePath ?: "", imageName = entity.imageFileName ?: ""
            ),
            relatedVideo = entity.relatedVideoDataEntity?.items?.map {
                assetItemEntityMapper.toDomain(it)
            },
            publishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_DETAILS,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            categoryTag = categoryTag,
            sharingUrl = sharingUrl,
            duration = entity.duration.toString(),
            contentSourceId = entity.contentSourceId,
            beautifiedDuration = null
        )
    }
}