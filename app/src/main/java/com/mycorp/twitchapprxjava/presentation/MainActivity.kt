package com.mycorp.twitchapprxjava.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.presentation.viewModel.MainActivityViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    lateinit var gamesListAdapter: GamesListAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(this))[MainActivityViewModel::class.java]
        initGamesListView()
        loadGames()
    }

    private fun initGamesListView() {
        gamesListAdapter = GamesListAdapter(arrayListOf())
        activityMainBinding.gamesRv.layoutManager =
            LinearLayoutManager(this)
        activityMainBinding.gamesRv.adapter = gamesListAdapter
    }

    private fun loadGames() {
        viewModel.getGamesDataListObserver().observe(this, {
            if (it != null) gamesListAdapter.addGames(it as ArrayList<GameData>)
            else Toast.makeText(
                this,
                "Error in fetching data",
                Toast.LENGTH_SHORT
            ).show()
        })
        viewModel.makeApiCall()
    }
}