package com.example.androidlibraryexample.di

import com.example.androidlibraryexample.BuildConfig
import com.example.androidlibraryexample.business.AuthRepositoryImpl
import com.example.androidlibraryexample.data.AuthRepository
import com.example.androidlibraryexample.data.ConfigManger
import com.sportzinteractive.baseprojectsetup.constants.CustomValues
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun isAvailable():Boolean{
        return true
    }

    @Provides
    @Singleton
    fun provideCustomValues(configManger: ConfigManger):CustomValues{
        return CustomValues("https://stg-rr.sportz.io/",
        "",
        "DemoApp",
        "",
        "6LdGSk0jAAAAAJFbgHx-wVyd4M5L_7Y_NHSa1GZW",
        BuildConfig.DEBUG)
    }

    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl):AuthRepository{
        return authRepositoryImpl
    }
}