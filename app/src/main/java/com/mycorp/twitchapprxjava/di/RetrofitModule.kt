package com.mycorp.twitchapprxjava.di

import com.mycorp.twitchapprxjava.BuildConfig
import com.mycorp.twitchapprxjava.api.FollowersApi
import com.mycorp.twitchapprxjava.api.GamesApi
import com.mycorp.twitchapprxjava.api.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(GamesApi::class.java) }
    single { get<Retrofit>().create(FollowersApi::class.java) }
}
