package com.sportzinteractive.baseprojectsetup.di

import com.sportzinteractive.baseprojectsetup.business.domain.repository.AuthRepositoryImpl
import com.sportzinteractive.baseprojectsetup.business.domain.repository.DetailsRepositoryImpl
import com.sportzinteractive.baseprojectsetup.business.domain.repository.GeneralRepositoryImpl
import com.sportzinteractive.baseprojectsetup.business.domain.repository.ListingRepositoryImpl
import com.sportzinteractive.baseprojectsetup.business.domain.repository.NotificationRepositoryImpl
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import com.sportzinteractive.baseprojectsetup.data.repository.DetailsRepository
import com.sportzinteractive.baseprojectsetup.data.repository.GeneralRepository
import com.sportzinteractive.baseprojectsetup.data.repository.ListingRepository
import com.sportzinteractive.baseprojectsetup.data.repository.NotificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun provideBaseRepository(generalRepository: GeneralRepositoryImpl): GeneralRepository

    @Binds
    @ViewModelScoped
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    fun provideListingRepository(listingRepositoryImpl: ListingRepositoryImpl): ListingRepository

    @Binds
    @ViewModelScoped
    fun provideDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository

    @Binds
    @ViewModelScoped
    fun provideNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository
}