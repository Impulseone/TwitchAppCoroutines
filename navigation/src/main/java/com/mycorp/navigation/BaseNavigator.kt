package com.mycorp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

abstract class BaseNavigator<T: BaseNavigationFlow> {
    lateinit var navController: NavController
    open fun setupNavController(navController: NavController) {
        this.navController = navController
    }
    open fun navigateToFlow(navigationFlow: T, directions: NavDirections? = null) {}
    open fun popBackStack(navigationFlow: MainNavigationFlow) {}
}