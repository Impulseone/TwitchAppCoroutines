package com.mycorp.twitchapprxjava.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.databinding.ActivityMainBinding
import com.mycorp.twitchapprxjava.presentation.gamesListView.GamesListAdapter
import com.mycorp.twitchapprxjava.presentation.viewModel.LoadingStatus
import com.mycorp.twitchapprxjava.presentation.viewModel.MainActivityViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val activityViewModel: MainActivityViewModel by viewModel()
    private var gamesListAdapter: GamesListAdapter? = null
    private val activityMainBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        loadGames()
    }

    private fun initViews() {
        with(activityMainBinding) {
            gamesListAdapter = GamesListAdapter()
            gamesRv.layoutManager =
                LinearLayoutManager(this@MainActivity)
            gamesRv.adapter = gamesListAdapter

            reportButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, RatingActivity::class.java))
            }

        }
    }

    private fun loadGames() {
        listenLoadingGames(activityViewModel.getGamesDataFromServerObserver())
    }

    private fun listenLoadingGames(gamesLiveData: MutableLiveData<Resource<List<GameData>>>) {
        gamesLiveData.observe(this, {
            when (it.loadingStatus) {
                LoadingStatus.LOADING -> {
                    changeProgressbarVisibility(View.VISIBLE)
                }
                LoadingStatus.SUCCESS -> {
                    changeProgressbarVisibility(View.GONE)
                    gamesListAdapter?.submitList(it.data as ArrayList<GameData>)
                }
                LoadingStatus.ERROR -> {
                    makeToast(it.message!!)
                }
            }
        })
    }

    private fun changeProgressbarVisibility(visibility: Int) {
        activityMainBinding.progressIndicator.visibility = visibility
    }

    private fun makeToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}