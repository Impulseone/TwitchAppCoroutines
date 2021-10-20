package com.mycorp.twitchapprxjava.presentation.viewModel

import android.view.View

data class GameDataViewState<out T>(val progressIndicatorVisibility: Int, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): GameDataViewState<T> = GameDataViewState(progressIndicatorVisibility = View.GONE, data = data, message = "get data success")

        fun <T> error(message: String): GameDataViewState<T> =
            GameDataViewState(progressIndicatorVisibility = View.GONE, data = null, message = message)

        fun <T> loading(): GameDataViewState<T> = GameDataViewState(progressIndicatorVisibility = View.VISIBLE, data = null, message = "loading started")
    }
}