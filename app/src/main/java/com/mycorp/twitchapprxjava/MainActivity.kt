package com.mycorp.twitchapprxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding
import com.mycorp.twitchapprxjava.model.GameData
import com.mycorp.twitchapprxjava.viewModel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:  MainActivityViewModel
    lateinit var gamesListAdapter: GamesListAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        gamesListAdapter = GamesListAdapter(arrayListOf())
        activityMainBinding.gamesRv.layoutManager =
            LinearLayoutManager(this)
        activityMainBinding.gamesRv.adapter = gamesListAdapter

        loadGames()
    }

   private fun loadGames() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getGamesDataListObserver().observe(this, {
            if(it != null) {
                with(gamesListAdapter) {
                    addGames(it as ArrayList<GameData>)
                    notifyDataSetChanged()
                }
            }
            else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
}