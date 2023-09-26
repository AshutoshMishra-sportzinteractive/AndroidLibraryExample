package com.sportzinteractive.baseprojectsetup.utils

import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.helper.BaseLocalStorageManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CustomRequestInterceptor @Inject constructor(
    private val baseInfo: BaseInfo,
    private val baseLocalStorageManager: BaseLocalStorageManager
) : Interceptor {

    @Throws(IllegalArgumentException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("auth", baseInfo.getApiAuthKey())
        val token = runBlocking { baseLocalStorageManager.getUserToken().firstOrNull()?:"" }
        token?.let {
            builder.addHeader("usertoken", it)
        }
        return chain.proceed(builder.build())
    }
}
