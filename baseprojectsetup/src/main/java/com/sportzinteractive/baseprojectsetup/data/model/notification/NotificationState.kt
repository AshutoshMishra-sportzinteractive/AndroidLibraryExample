package com.sportzinteractive.baseprojectsetup.data.model.notification

sealed class NotificationState{
    class Success(val OnOrOff:Boolean) : NotificationState()
    class Failure(val message: String) : NotificationState()
}
