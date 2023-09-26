package com.sportzinteractive.baseprojectsetup.data.model.clubs


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("in_app_browser")
    val inAppBrowser: Boolean?,
    @SerializedName("is_external_webview")
    val isExternalWebview: Boolean?,
    @SerializedName("is_webview")
    val isWebview: Boolean?,
    @SerializedName("menu_id")
    val menuId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("short_name")
    val shortName: String?,
    @SerializedName("webview_url")
    val webviewUrl: String?
)