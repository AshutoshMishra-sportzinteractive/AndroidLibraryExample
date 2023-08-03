package com.sportzinteractive.baseprojectsetup.utils

import com.sportzinteractive.baseprojectsetup.constants.CustomValues
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CustomRequestInterceptor @Inject constructor(
    private val customValues: CustomValues
) : Interceptor {

    @Throws(IllegalArgumentException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("auth", customValues.apiAuthKey)
        val token = runBlocking { customValues.userToken }
        token?.let {
            builder.addHeader("usertoken", it)
        }
        return chain.proceed(builder.build());
    }
}
