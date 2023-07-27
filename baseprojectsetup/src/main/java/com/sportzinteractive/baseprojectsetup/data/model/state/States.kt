package com.sportzinteractive.baseprojectsetup.data.model.state

import com.google.gson.annotations.SerializedName

data class State(
    @SerializedName("status")
    val status:Int?,
    @SerializedName("content")
    val content:Content
)

data class Content(
    @SerializedName("states")
    val states:List<StateObject>
)

data class StateObject(
    @SerializedName("name")
    val name:String,
    @SerializedName("state_id")
    val stateId:Int?,
    @SerializedName("country_id")
    val countryId:Int?,

    ){
    override fun toString(): String = name
}