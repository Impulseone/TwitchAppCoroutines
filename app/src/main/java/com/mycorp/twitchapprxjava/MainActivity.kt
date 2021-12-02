package com.mycorp.twitchapprxjava

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mycorp.navigation.BaseNavigationFlow
import com.mycorp.navigation.MainNavigationFlow
import com.mycorp.navigation.Navigator
import com.mycorp.navigation.ToFlowNavigatable
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), ToFlowNavigatable {

    private val binding: ActivityMainBinding by viewBinding()
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment).navController
    }
    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.navController = navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun navigateToFlow(flow: BaseNavigationFlow, directions: NavDirections?) {
        navigator.navigateToFlow(flow, directions)
    }

    override fun popBackStack(
        baseNavigationFlow: BaseNavigationFlow,
        lifecycleOwner: LifecycleOwner
    ) {
        when (baseNavigationFlow) {
            is MainNavigationFlow.GamesFlow -> onBackPressedDispatcher.addCallback(lifecycleOwner) {
                finish()
            }
            is MainNavigationFlow.FavoriteGamesFlow -> onBackPressedDispatcher.addCallback(lifecycleOwner) {
                finish()
            }
        }
    }
}