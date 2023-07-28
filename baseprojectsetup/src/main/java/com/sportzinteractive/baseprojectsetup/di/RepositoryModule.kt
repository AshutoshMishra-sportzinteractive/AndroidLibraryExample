package com.sportzinteractive.baseprojectsetup.di

import com.sportzinteractive.baseprojectsetup.business.domain.AuthRepositoryImpl
import com.sportzinteractive.baseprojectsetup.business.domain.GeneralRepositoryImpl
import com.sportzinteractive.baseprojectsetup.data.repository.AuthRepository
import com.sportzinteractive.baseprojectsetup.data.repository.GeneralRepository
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
    fun provideBaseRepository(generalRepository: GeneralRepositoryImpl):GeneralRepository

    @Binds
    @ViewModelScoped
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl):AuthRepository

}