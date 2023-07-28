package com.sportzinteractive.baseprojectsetup.di

import com.sportzinteractive.baseprojectsetup.data.services.AuthService
import com.sportzinteractive.baseprojectsetup.data.services.GeneralService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {


    @Provides
    @ViewModelScoped
    fun provideBaseService(retrofit: Retrofit): GeneralService =
        retrofit.create(GeneralService::class.java)

    @Provides
    @ViewModelScoped
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

}