package com.sportzinteractive.baseprojectsetup.data.model.auth

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthBaseResponse(
    @SerializedName("data")
    val responseData: ResponseData?,
    @SerializedName("content")
    val content: ResponseContent?,
    @SerializedName("status")
    val status: Int?,
)

data class ResponseContent(
    @SerializedName("status")
    val status: Int?,
)

data class ResponseData(
    @SerializedName("user_guid")
    val userGuid: String?,
    @SerializedName("user_id")
    val userId:String?,
    @SerializedName("email_id")
    val email: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: UserEntity?,
    @SerializedName("waf_user_guid")
    val waf_id: String?,
    @SerializedName("error")
    val error: Error?,

    @SerializedName("email_verified")
    val emailVerified: String?,
    @SerializedName("mobile_verified")
    val mobileVerified: String?,
    @SerializedName("created_date")
    val createdDateTime: String?,
    @SerializedName("is_first_login")
    val isFirstTimeLogin: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("epoch_timestamp")
    val epoch_timestamp: String?,

) : Serializable

data class Error(
    val code: String?,
    val message: String?,
)