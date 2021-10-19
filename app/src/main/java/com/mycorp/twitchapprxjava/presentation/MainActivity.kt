package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainBinding.reportButton.setOnClickListener {
            createRatingFragment()
        }
        createGamesListFragment()
    }

    private fun createGamesListFragment() {
        val ft = supportFragmentManager.beginTransaction()
        val gamesListFragment = GamesListFragment()
        ft.replace(R.id.fragment_layout, gamesListFragment)
        ft.commit()
    }
    private fun createRatingFragment() {
        val ft = supportFragmentManager.beginTransaction()
        val ratingFragment = RatingFragment()
        ft.replace(R.id.fragment_layout, ratingFragment)
        ft.commit()
    }
}