package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentGamesListBinding
import com.mycorp.twitchapprxjava.presentation.gamesListView.GamesListAdapter
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.GamesListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesListFragment : BaseFragment<GamesListViewModel>(R.layout.fragment_games_list) {

    private val binding: FragmentGamesListBinding by viewBinding()
    private var gamesListAdapter: GamesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    private fun initViews() {
        with(binding) {
            gamesListAdapter = GamesListAdapter()
            gamesRv.layoutManager =
                LinearLayoutManager(context)
            gamesRv.adapter = gamesListAdapter

            reportButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_gamesListFragment_to_ratingFragment)
            )
        }
    }

    override fun bindVm() {
        super.bindVm()
        viewModel.gamesLiveData().observe(viewLifecycleOwner, {
            changeProgressbarVisibility(it.progressIndicatorVisibility)
            gamesListAdapter?.submitList(it.data)
        })
    }

    private fun changeProgressbarVisibility(visibility: Boolean) {
        binding.progressIndicator.isVisible = visibility
    }

    override val viewModel: GamesListViewModel by viewModel()

}