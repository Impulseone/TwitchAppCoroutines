package com.mycorp.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections

interface ToFlowNavigatable {
    fun navigateToFlow(flow: BaseNavigationFlow, directions: NavDirections?)
    fun popBackStack(baseNavigationFlow: BaseNavigationFlow, lifecycleOwner: LifecycleOwner)
}