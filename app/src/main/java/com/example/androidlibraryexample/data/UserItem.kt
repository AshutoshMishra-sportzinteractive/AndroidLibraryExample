package com.example.androidlibraryexample.data

import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("website")
    val website: String?
){

}