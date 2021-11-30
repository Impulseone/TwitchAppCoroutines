package com.mycorp.navigation

import androidx.navigation.NavDirections

class MainNavigator: BaseNavigator() {
    override fun navigateToFlow(directions: NavDirections) {
        navController.navigate(directions)
    }
}