package com.example.androidlibraryexample.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getFakeStuff(
        @Path("id") id:Int
    ): Response<UserItem>

}