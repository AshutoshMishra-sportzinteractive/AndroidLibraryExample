package com.sportzinteractive.baseprojectsetup.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultOkHttps

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotificationOkHttps