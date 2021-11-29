package com.mycorp.navigation

import androidx.navigation.NavDirections

class MainNavigator: BaseNavigator<MainNavigationFlow>() {

    var bottomBarState: ((Boolean) -> Unit)? = null

    override fun navigateToFlow(navigationFlow: MainNavigationFlow, directions: NavDirections?) {
        when (navigationFlow) {
//            MainNavigationFlow.FavoriteGames -> navController.navigate(MainNavGraphDirections.actionGlobalNotesFlow())

        }

        bottomBarState?.invoke(navigationFlow.checkBottomBarState())
    }

    override fun popBackStack(navigationFlow: MainNavigationFlow) {
        navController.popBackStack()

        bottomBarState?.invoke(navigationFlow.checkBottomBarState())
    }

}