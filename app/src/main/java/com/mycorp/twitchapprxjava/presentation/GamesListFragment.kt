package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.databinding.FragmentGamesListBinding
import com.mycorp.twitchapprxjava.presentation.gamesListView.GamesListAdapter
import com.mycorp.twitchapprxjava.presentation.viewModel.LoadingStatus
import com.mycorp.twitchapprxjava.presentation.viewModel.MainActivityViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesListFragment : Fragment() {

    private val fragmentViewBinding: FragmentGamesListBinding by viewBinding()
    private val activityViewModel: MainActivityViewModel by viewModel()
    private var gamesListAdapter: GamesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_games_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        initViews()
        listenLoadingGames(activityViewModel.getGamesDataFromServerObserver())
    }

    private fun initViews() {
        with(fragmentViewBinding) {

            gamesListAdapter = GamesListAdapter()
            gamesRv.layoutManager =
                LinearLayoutManager(context)
            gamesRv.adapter = gamesListAdapter

            reportButton.setOnClickListener {
                findNavController().navigate(R.id.ratingFragment)
            }

        }
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
        fragmentViewBinding.progressIndicator.visibility = visibility
    }

    private fun makeToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}