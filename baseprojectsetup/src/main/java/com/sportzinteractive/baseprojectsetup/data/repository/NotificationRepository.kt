package com.sportzinteractive.baseprojectsetup.data.repository

import com.sportzinteractive.baseprojectsetup.data.model.notification.NotificationRequest
import com.sportzinteractive.baseprojectsetup.data.model.notification.NotificationState
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    fun notification(url:String, notificationRequest: NotificationRequest): Flow<NotificationState>

}