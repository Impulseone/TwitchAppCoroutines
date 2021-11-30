package com.mycorp.navigation

sealed class MainNavigationFlow : BaseNavigationFlow() {
    object GamesFlow : MainNavigationFlow()
    object GameFlow : MainNavigationFlow()
    object FavoriteGamesFlow : MainNavigationFlow()
    object FollowersFlow : MainNavigationFlow()
    object RatingFlow : MainNavigationFlow()
}
