package com.sportzinteractive.baseprojectsetup.constants


interface BaseInfo {
    fun getBaseUrl():String
    fun getApiAuthKey():String
    fun getPreferenceDataStoreName():String
    fun getCaptchaSiteKey():String
    fun getIsDebugMode(): Boolean
    fun getContentSharingUrl(entityCategory: String?, titleAlias: String?): String
    fun getContentImageUrl(
        imagePath: String?,
        imageName: String?,
        imageRatio: String? = null
    ): String

    fun getTeamLogo(
        clubId: String
    ): String

    fun getBaseContentImageUrl(): String
    fun getClubImageUrl(path: String?): String
    fun getNotificationBaseUrl(): String

    fun getNotificationAuthKey(): String
    fun getVersionName(): String

    fun getPlayerImg(
        playerId: String
    ): String
}