package com.mycorp.navigation

sealed class MainNavigationFlow : BaseNavigationFlow() {
    object FavoriteGames : MainNavigationFlow()
    object Games : MainNavigationFlow()

    fun checkBottomBarState(): Boolean {
        return when (this) {
            is FavoriteGames -> true
            else -> false
        }
    }
}
