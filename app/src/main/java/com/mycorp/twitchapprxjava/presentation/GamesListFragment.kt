package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.databinding.FragmentGamesListBinding
import com.mycorp.twitchapprxjava.presentation.gamesListView.GamesListAdapter
import com.mycorp.twitchapprxjava.presentation.viewModel.LoadingStatus
import com.mycorp.twitchapprxjava.presentation.viewModel.GamesListViewModel
import com.mycorp.twitchapprxjava.presentation.viewModel.GameDataViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesListFragment : FooBaseFragment<GamesListViewModel>() {

    private val fragmentViewBinding: FragmentGamesListBinding by viewBinding()
    private val gamesListViewModel: GamesListViewModel by viewModel()
    private var gamesListAdapter: GamesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_games_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    override fun onStart() {
        super.onStart()
        listenLoadingGames(viewModel.getGamesDataFromServerObserver())
    }

    private fun initViews() {
        with(fragmentViewBinding) {

            gamesListAdapter = GamesListAdapter()
            gamesRv.layoutManager =
                LinearLayoutManager(context)
            gamesRv.adapter = gamesListAdapter

            reportButton.setOnClickListener (
                Navigation.createNavigateOnClickListener(R.id.action_gamesListFragment_to_ratingFragment)
            )

        }
    }

    private fun listenLoadingGames(gamesLiveData: MutableLiveData<GameDataViewState<List<GameData>>>) {
        gamesLiveData.observe(this, {
            changeProgressbarVisibility(it.progressIndicatorVisibility)
            gamesListAdapter?.submitList(it.data)
            viewModel.showToast(it.message!!)
        })
    }
    private fun changeProgressbarVisibility(visibility: Int) {
        fragmentViewBinding.progressIndicator.visibility = visibility
    }

    override val viewModel: GamesListViewModel
        get() = gamesListViewModel
}