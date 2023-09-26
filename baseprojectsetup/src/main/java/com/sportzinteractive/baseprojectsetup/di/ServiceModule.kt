package com.sportzinteractive.baseprojectsetup.di

import com.sportzinteractive.baseprojectsetup.data.services.*
import com.sportzinteractive.baseprojectsetup.di.qualifier.DefaultRetrofit
import com.sportzinteractive.baseprojectsetup.di.qualifier.NotificationRetrofit
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
    fun provideBaseService(@DefaultRetrofit retrofit: Retrofit): GeneralService =
        retrofit.create(GeneralService::class.java)

    @Provides
    @ViewModelScoped
    fun provideAuthService(@DefaultRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @ViewModelScoped
    fun provideListingServiceApi(@DefaultRetrofit retrofit: Retrofit): ListingService {
        return retrofit.create(ListingService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailsServiceApi(@DefaultRetrofit retrofit: Retrofit): DetailsService {
        return retrofit.create(DetailsService::class.java)
    }


    @Provides
    @ViewModelScoped
    fun provideNotificationService(@NotificationRetrofit retrofit: Retrofit): NotificationService {
        return retrofit.create(NotificationService::class.java)
    }

}