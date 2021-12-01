package com.mycorp.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: BaseNavigationFlow) = when (navigationFlow) {
        MainNavigationFlow.GamesFlow -> navController.navigate(MainNavGraphDirections.actionGlobalGamesFlow())
        MainNavigationFlow.FavoriteGamesFlow -> navController.navigate(MainNavGraphDirections.actionGlobalFavoriteGamesFlow())
        else -> navController.navigate(MainNavGraphDirections.actionGlobalGamesFlow())
    }
}