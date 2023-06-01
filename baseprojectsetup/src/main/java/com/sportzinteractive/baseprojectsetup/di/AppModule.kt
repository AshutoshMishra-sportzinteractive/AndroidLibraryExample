package com.sportzinteractive.baseprojectsetup.di

import android.content.Context
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.sportzinteractive.baseprojectsetup.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppName():String{
        return "abc"
    }

    @Provides
    @Singleton
    fun provideCustomTabIntent(@ApplicationContext context: Context): CustomTabsIntent {
        val toolbarColor = ContextCompat.getColor(context, R.color.chrome_tab_color)
        return CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder().setToolbarColor(toolbarColor).build()
            )
            .build()
    }

}