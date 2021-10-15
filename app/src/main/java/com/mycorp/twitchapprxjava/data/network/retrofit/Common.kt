package com.mycorp.twitchapprxjava.data.network.retrofit

object Common {
    val BASE_URL = "https://api.twitch.tv/"
    val retrofitService: ApiService
        get() = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
}