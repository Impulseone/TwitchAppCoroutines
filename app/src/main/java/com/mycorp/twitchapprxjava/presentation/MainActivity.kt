package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding
import androidx.navigation.ui.NavigationUI

import androidx.navigation.fragment.NavHostFragment
import com.mycorp.twitchapprxjava.R

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navController
        )
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            navHostFragment.navController.navigate(item.itemId)
            true
        }
    }
}