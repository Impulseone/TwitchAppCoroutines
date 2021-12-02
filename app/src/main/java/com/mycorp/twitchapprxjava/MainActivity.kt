package com.mycorp.twitchapprxjava

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.navigation.*
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

    override fun onBackPressed() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val onBackPressed = navHostFragment.childFragmentManager.fragments[0] as? OnBackPressed
        if (onBackPressed != null) {
            finish()
        } else super.onBackPressed()
    }
}