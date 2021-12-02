package com.mycorp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: BaseNavigationFlow, directions: NavDirections?) =
        when (navigationFlow) {
            MainNavigationFlow.GamesFlow, MainNavigationFlow.FavoriteGamesFlow -> navController.navigate(
                MainNavGraphDirections.actionGlobalGamesFlow()
            )
            MainNavigationFlow.GameFlow, MainNavigationFlow.FollowersFlow, MainNavigationFlow.RatingFlow -> directions?.let {
                navController.navigate(
                    it
                )
            }
            else -> navController.navigate(MainNavGraphDirections.actionGlobalGamesFlow())
        }
}