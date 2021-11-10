package com.mycorp.twitchapprxjava.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(ACCEPT_HEADER, ACCEPT_HEADER_VALUE)
            .addHeader(CLIENT_ID, CLIENT_ID_HEADER_VALUE)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val ACCEPT_HEADER = "Accept"
        private const val CLIENT_ID = "Client-ID"

        private const val ACCEPT_HEADER_VALUE = "application/vnd.twitchtv.v5+json"
        private const val CLIENT_ID_HEADER_VALUE = "ahuoi1tl0qmqbyi8jo8nitbmuaad7w"
    }

}