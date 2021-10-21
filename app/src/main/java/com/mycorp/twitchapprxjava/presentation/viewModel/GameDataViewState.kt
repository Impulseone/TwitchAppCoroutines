package com.mycorp.twitchapprxjava.presentation.viewModel

data class GameDataViewState<out T>(val progressIndicatorVisibility: Boolean, val data: T?) {
    companion object {
        fun <T> success(data: T): GameDataViewState<T> = GameDataViewState(progressIndicatorVisibility = false, data = data)

        fun <T> error(): GameDataViewState<T> =
            GameDataViewState(progressIndicatorVisibility = false, data = null)

        fun <T> loading(): GameDataViewState<T> = GameDataViewState(progressIndicatorVisibility = true, data = null)
    }
}