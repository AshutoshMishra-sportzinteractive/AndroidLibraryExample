package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.Player
import com.sportzinteractive.baseprojectsetup.data.model.footballsquad.PlayerItem
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SquadEntityMapper @Inject constructor(
    val baseInfo: BaseInfo
) : EntityMapper<Player, PlayerItem> {

    override fun toDomain(entity: Player): PlayerItem {
        return PlayerItem(
            playerId = entity.playerId ?: 0,
            playerFullName = entity.fullName ?: "",
            playerShortName = entity.playerShortName ?: "",
            positionName = entity.positionName ?: "",
            playerImgUrl = baseInfo.getPlayerImg(entity.playerId.toString()),
            positionId = entity.positionId ?: 0

            /*assetId = entity.assetId,
            beautifiedDuration = CalendarUtils.convertDateStringToSpecifiedDateString(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST,
                requiredDateFormat = "dd MMM, YYYY"
            ),
            assetGuid = entity.assetGuid,
            assetTitle = entity.assetTitle,
            titleAlias = entity.titleAlias,
            assetTypeId = entity.assetTypeId,
            secondaryEntityRoleMapId = entity.secondaryEntityRoleMapId,
            imageUrl = baseInfo.getContentImageUrl(
                imagePath = entity.imagePath ?: "",
                imageName = entity.imageFileName ?: "",
                imageRatio = "16-9"
            ),
            sharingUrl = baseInfo.getContentSharingUrl(
                entityCategory = entity.secEntUrl.orEmpty(),
                titleAlias = entity.titleAlias.orEmpty()
            ),
            totalAssets = entity.totalAssets?.let { it.toInt() + 1 }.toString(),
            totalReacts = null,
            description = entity.description,
            offer = entity.offer,
            price = entity.price,
            discountPrice = entity.discountPrice,
            externalLink = entity.externalLink,
            contentSourceId = entity.contentSourceId,
            bannerImage = entity.bannerImage,
            bannerLink = entity.bannerLink,
            publishedDate = CalendarUtils.getPublishedDuration(
                dateString = entity.publishedDate,
                dateFormat = CalendarUtils.PUBLISHED_ASSET_LIST,
                requiredDateFormat = CalendarUtils.PUBLISHED_DISPLAY_DATE_FORMAT
            ),
            webViewUrl = entity.webviewUrl,
            isWebView = entity.isWebview,
            inAppBrowser = entity.in_app_browser,
            isExternalWebView = entity.is_external_webview*/
        )
    }
}