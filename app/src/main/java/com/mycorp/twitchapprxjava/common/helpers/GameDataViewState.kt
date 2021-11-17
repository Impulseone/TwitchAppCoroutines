package com.mycorp.twitchapprxjava.common.helpers

data class GameDataViewState<out T>(val progressIndicatorVisibility: Boolean, val data: T?) {
    companion object {
        fun <T> success(data: T): GameDataViewState<T> = GameDataViewState(progressIndicatorVisibility = false, data = data)

        fun <T> error(): GameDataViewState<T> =
            GameDataViewState(progressIndicatorVisibility = false, data = null)
    }
}