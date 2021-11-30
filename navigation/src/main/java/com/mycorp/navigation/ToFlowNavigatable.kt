package com.mycorp.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections

interface ToFlowNavigatable {
    fun navigateToFlow(directions: NavDirections)
    fun popBackStack(baseNavigationFlow: BaseNavigationFlow, lifecycleOwner: LifecycleOwner)
}