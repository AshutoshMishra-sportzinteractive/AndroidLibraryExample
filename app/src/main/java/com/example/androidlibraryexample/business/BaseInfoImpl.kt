package com.example.androidlibraryexample.business

import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseInfoImpl @Inject constructor(): BaseInfo {

    override fun getBaseUrl(): String {
        return "https://stg-isl.sportz.io/"
    }

    override fun getApiAuthKey(): String {
        return ""
    }

    override fun getPreferenceDataStoreName(): String {
        return "ISLApp"
    }

    override fun getCaptchaSiteKey(): String {
        return "6LcFSVYnAAAAAPFwu04x53a3xGOoBEMq1lz4AaWE"
    }

    override fun getIsDebugMode(): Boolean {
        return true
    }

    override fun getContentSharingUrl(
        entityCategory: String?,
        titleAlias: String?
    ): String {
        return "https://stg-isl.sportz.io/"
    }

    override fun getContentImageUrl(
        imagePath: String?,
        imageName: String?,
        imageRatio: String?
    ): String {
        return "https://stg-isl.sportz.io/"
    }

    override fun getTeamLogo(clubId: String): String {
        return "https://stg-isl.sportz.io/"
    }


    override fun getBaseContentImageUrl(): String {
        return "https://stg-isl.sportz.io/"
    }

    override fun getClubImageUrl(basePath: String?): String {
        return "https://stg-isl.sportz.io/"
    }

    override fun getNotificationBaseUrl(): String {
        return "https://pkl-notifications.sportz.io"
    }

    override fun getNotificationAuthKey(): String {
        return ""
    }

    override fun getVersionName(): String {
        return "1.0.0"
    }

    override fun getPlayerImg(playerId: String): String {
        return "https://stg-isl.sportz.io/"
    }
}