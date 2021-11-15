package com.mycorp.twitchapprxjava.screens.games

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.common.extensions.setIgnoreLastDivider
import com.mycorp.twitchapprxjava.databinding.FragmentGamesBinding
import com.mycorp.twitchapprxjava.common.fragment.BaseFragment
import com.mycorp.twitchapprxjava.screens.games.adapter.PagedGamesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesFragment : BaseFragment<GamesVM>(R.layout.fragment_games) {

    private val binding: FragmentGamesBinding by viewBinding()
    private var pagedAdapter: PagedGamesAdapter? = null

    override val viewModel: GamesVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
        viewModel.init()
    }

    private fun initViews() {
        with(binding) {
            pagedAdapter = PagedGamesAdapter {
                viewModel.gameItemClicked(it)
            }
            gamesRv.apply {
                setIgnoreLastDivider(R.drawable.shape_game_divider)
                adapter = this@GamesFragment.pagedAdapter
            }
            rateButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_gamesFragment_to_ratingFragment)
            )
        }
    }

    override fun bindVm() {
        super.bindVm()

        bindData(viewModel.pagedGamesLiveData) {
            binding.progressIndicator.isVisible = it.progressIndicatorVisibility
            pagedAdapter?.submitList(it.data)
        }
        bindCommand(viewModel.launchGameScreenCommand) {
            it?.let {
                findNavController().navigate(
                    GamesFragmentDirections.actionGamesFragmentToGameFragment(
                        it
                    )
                )
            }
        }
    }
}