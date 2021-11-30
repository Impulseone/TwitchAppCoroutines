package com.mycorp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

abstract class BaseNavigator {
    lateinit var navController: NavController
    open fun setupNavController(navController: NavController) {
        this.navController = navController
    }
    open fun navigateToFlow(directions: NavDirections) {}
}