package com.example.androidlibraryexample.data

import com.example.androidlibraryexample.business.BaseInfoImpl
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BaseModule {

    @Binds
    @Singleton
    fun provideBaseRepository(baseInfoImpl: BaseInfoImpl): BaseInfo


}