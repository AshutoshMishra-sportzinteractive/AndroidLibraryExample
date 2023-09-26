package com.sportzinteractive.baseprojectsetup.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.di.qualifier.*
import com.sportzinteractive.baseprojectsetup.utils.CurlLoggingInterceptor
import com.sportzinteractive.baseprojectsetup.utils.CustomRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Singleton
    @Provides
    fun providesCurlInterceptor(baseInfo: BaseInfo): CurlLoggingInterceptor {
        return CurlLoggingInterceptor("cURL",baseInfo)
    }

    @Singleton
    @Provides
    @DefaultOkHttps
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        curlLoggingInterceptor: CurlLoggingInterceptor,
        customRequestInterceptor: CustomRequestInterceptor,
        baseInfo: BaseInfo
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(customRequestInterceptor)
            .apply {
                if (baseInfo.getIsDebugMode()) {
                    addInterceptor(httpLoggingInterceptor)
                    addInterceptor(curlLoggingInterceptor)
                    addNetworkInterceptor(StethoInterceptor())
                }
            }.build()
    }

    @Singleton
    @Provides
    @NotificationOkHttps
    fun providesNotificationOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        curlLoggingInterceptor: CurlLoggingInterceptor,
        baseInfo: BaseInfo
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                if (baseInfo.getIsDebugMode()) {
                    addInterceptor(httpLoggingInterceptor)
                    addInterceptor(curlLoggingInterceptor)
                    addNetworkInterceptor(StethoInterceptor())
                }
            }.build()
    }


    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @DefaultRetrofit
    fun provideRetrofit(
        @DefaultOkHttps okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        baseInfo: BaseInfo
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseInfo.getBaseUrl())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()


    @Provides
    @Singleton
    @NotificationRetrofit
    fun provideNotificationRetrofit(
        @NotificationOkHttps okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        baseInfo: BaseInfo
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseInfo.getBaseUrl())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
}