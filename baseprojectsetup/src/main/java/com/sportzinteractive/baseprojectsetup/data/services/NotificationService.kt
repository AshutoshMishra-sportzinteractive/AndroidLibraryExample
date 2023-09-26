package com.sportzinteractive.baseprojectsetup.data.services

import com.sportzinteractive.baseprojectsetup.data.model.notification.NotificationRequest
import com.sportzinteractive.baseprojectsetup.data.model.notification.NotificationResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface NotificationService {

    @Headers("Content-Type: application/json")
    @POST
    suspend fun subscribeNotification(
        @Header("auth") auth: String,
        @Url url:String,
        @Body notificationRequest: NotificationRequest
    ): NotificationResponse

}