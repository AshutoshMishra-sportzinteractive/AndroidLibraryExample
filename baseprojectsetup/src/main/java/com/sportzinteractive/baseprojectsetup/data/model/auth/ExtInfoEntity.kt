package com.sportzinteractive.baseprojectsetup.data.model.auth


import com.google.gson.annotations.SerializedName

data class ExtInfoEntity(
    @SerializedName("clubs")
    val clubs: List<String?>?
)