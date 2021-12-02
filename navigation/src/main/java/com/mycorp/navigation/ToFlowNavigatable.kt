package com.mycorp.navigation

import androidx.navigation.NavDirections

interface ToFlowNavigatable {
    fun navigateToFlow(flow: BaseNavigationFlow, directions: NavDirections?)
}