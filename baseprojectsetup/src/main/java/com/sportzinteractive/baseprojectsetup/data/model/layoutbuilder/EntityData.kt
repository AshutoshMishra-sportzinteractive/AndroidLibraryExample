package com.sportzinteractive.baseprojectsetup.data.model.layoutbuilder


import com.google.gson.annotations.SerializedName

data class EntityData(
    @SerializedName("asset_id")
    val assetId: Int?,
    @SerializedName("asset_type_id")
    val assetTypeId: Int?,
    @SerializedName("canonical")
    val canonical: String?,
    @SerializedName("ent_disp_name")
    val entDispName: String?,
    @SerializedName("entity_role_map_id")
    val entityRoleMapId: Int?,
    @SerializedName("entity_url")
    val entityUrl: Any?,
    @SerializedName("is_lang")
    val isLang: Int?,
    @SerializedName("is_linkable")
    val isLinkable: Int?,
    @SerializedName("is_visible")
    val isVisible: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("priority")
    val priority: Int?
)