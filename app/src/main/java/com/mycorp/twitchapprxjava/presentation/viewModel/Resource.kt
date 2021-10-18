package com.mycorp.twitchapprxjava.presentation.viewModel

data class Resource<out T>(val loadingStatus: LoadingStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(loadingStatus = LoadingStatus.SUCCESS, data = data, message = "get data success")

        fun <T> error(message: String): Resource<T> =
            Resource(loadingStatus = LoadingStatus.ERROR, data = null, message = message)

        fun <T> loading(): Resource<T> = Resource(loadingStatus = LoadingStatus.LOADING, data = null, message = "loading started")
    }
}