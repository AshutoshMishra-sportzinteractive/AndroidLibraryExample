package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName

data class Module(
    @SerializedName("component_id")
    val componentId: Int?,
    @SerializedName("title")
    val displayTitle: String?,
    @SerializedName("fetch_from")
    val fetchFrom: Int?,
    @SerializedName("meta_info")
    val metaInfo: MetaInfo?,
    @SerializedName("selector")
    val selector: String?,
    @SerializedName("show_in_app")
    val showInApp: Int?,
    @SerializedName("show_in_mobile")
    val showInMobile: Int?,
    @SerializedName("show_in_web")
    val showInWeb: Int?,
    @SerializedName("template_component_map_id")
    val templateComponentMapId: Int?,
    @SerializedName("template_id")
    val templateId: Int?,
    @SerializedName("widget_data")
    val widgetData: WidgetData?
)