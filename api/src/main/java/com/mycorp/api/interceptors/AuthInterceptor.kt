package com.mycorp.api.interceptors

import com.mycorp.api.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(BuildConfig.ACCEPT_HEADER, BuildConfig.ACCEPT_HEADER_VALUE)
            .addHeader(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_ID_HEADER_VALUE)
            .build()
        return chain.proceed(newRequest)
    }
}