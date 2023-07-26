package com.sportzinteractive.baseprojectsetup.di

import com.sportzinteractive.baseprojectsetup.business.domain.BaseRepositoryImpl
import com.sportzinteractive.baseprojectsetup.data.repository.BaseRepository
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
    fun provideAuthRepository(baseRepositoryImpl: BaseRepositoryImpl):BaseRepository

}