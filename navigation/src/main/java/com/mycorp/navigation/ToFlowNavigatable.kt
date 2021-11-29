package com.mycorp.navigation

import androidx.navigation.NavDirections

interface ToFlowNavigatable {
    fun navigateToFlow(flow: MainNavigationFlow)
    fun navigateToFlow(flow: MainNavigationFlow, directions: NavDirections)
    fun popBackStack(flow: MainNavigationFlow)
}