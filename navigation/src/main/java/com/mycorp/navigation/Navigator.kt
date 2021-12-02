package com.mycorp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: BaseNavigationFlow, directions: NavDirections?) =
        when (navigationFlow) {
            MainNavigationFlow.GamesFlow -> navController.navigate(MainNavGraphDirections.actionGlobalGamesFlow())
            MainNavigationFlow.FavoriteGamesFlow -> navController.navigate(MainNavGraphDirections.actionGlobalFavoriteGamesFlow())
            MainNavigationFlow.GameFlow -> directions?.let { navController.navigate(it) }
            MainNavigationFlow.FollowersFlow -> directions?.let { navController.navigate(it) }
            MainNavigationFlow.RatingFlow -> navController.navigate(MainNavGraphDirections.actionGlobalRatingFlow())
            else -> navController.navigate(MainNavGraphDirections.actionGlobalGamesFlow())
        }
}