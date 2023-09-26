package com.sportzinteractive.baseprojectsetup.data.mapper.details


import com.sportzinteractive.baseprojectsetup.business.domain.model.details.ArticleDetail
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.assetdetails.article.ArticleDataEntity
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleDataEntityMapper @Inject constructor(
    private val baseInfo: BaseInfo
) : EntityMapper<ArticleDataEntity, ArticleDetail> {

    override fun toDomain(entity: ArticleDataEntity): ArticleDetail {
        val entityDataPriority2 = entity.entityData?.find { it.priority == 2 }
        val categoryTag = entityDataPriority2?.entDispName ?: "Article"
        val sharingUrl = baseInfo.getContentSharingUrl(
            entityCategory = entityDataPriority2?.canonical ?: "/news",
            titleAlias = entity.slugUrl ?: ""
        )

        return ArticleDetail(
            articleId = entity.articleId,
            assetType = entity.assetType,
            assetTypeId = entity.assetTypeId,
            fullText = entity.fullText,
            publishedDate = entity.publishedDate,
            shortTitle = entity.shortTitle,
            title = entity.title,
            titleAlias = entity.titleAlias,
            categoryTag = categoryTag,
            sharingUrl = sharingUrl,
            imageUrl = baseInfo.getContentImageUrl(
                entity.imageFilePath, entity.imageFileName,"4-3"
            ),
            displayPublishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_DETAILS,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            introText = entity.introText
        )
    }
}