package com.mycorp.twitchapprxjava.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding
import com.mycorp.twitchapprxjava.presentation.gamesListView.GamesListAdapter
import com.mycorp.twitchapprxjava.presentation.viewModel.LoadingStatus
import com.mycorp.twitchapprxjava.presentation.viewModel.MainActivityViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.MainViewModelFactory
import com.mycorp.twitchapprxjava.presentation.viewModel.Resource


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    lateinit var gamesListAdapter: GamesListAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory())[MainActivityViewModel::class.java]
        initGamesListView()
        setReportButton()
        loadGames()
    }

    private fun initGamesListView() {
        gamesListAdapter = GamesListAdapter()
        activityMainBinding.gamesRv.layoutManager =
            LinearLayoutManager(this)
        activityMainBinding.gamesRv.adapter = gamesListAdapter
    }

    private fun changeProgressbarVisibility(visibility: Int){
        activityMainBinding.progressIndicator.visibility = visibility
    }

    private fun listenLoadingGames(gamesLiveData: MutableLiveData<Resource<List<GameData>>>){
        gamesLiveData.observe(this, {
            when (it.loadingStatus) {
                LoadingStatus.LOADING -> {
                    changeProgressbarVisibility(View.VISIBLE)
                }
                LoadingStatus.SUCCESS -> {
                    changeProgressbarVisibility(View.GONE)
                    gamesListAdapter.submitList(it.data as ArrayList<GameData>)
                }
                LoadingStatus.ERROR -> {
                    makeToast(it.message!!)
                }
            }
        })
    }

    private fun loadGames() {
        listenLoadingGames(viewModel.getGamesDataFromServerObserver())
    }

    private fun makeToast(message:String){
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setReportButton() {
        activityMainBinding.reportButton.setOnClickListener {
            startActivity(Intent(this, RatingActivity::class.java))
        }
    }
}