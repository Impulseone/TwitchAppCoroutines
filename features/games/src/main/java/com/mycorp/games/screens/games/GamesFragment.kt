package com.mycorp.games.screens.games

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.common.extensions.setIgnoreLastDivider
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.games.R
import com.mycorp.games.databinding.FragmentGamesBinding
import com.mycorp.games.screens.games.adapter.PagedGamesAdapter
import com.mycorp.navigation.MainNavigationFlow
import com.mycorp.navigation.OnBackPressed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesFragment : BaseFragment<GamesViewModel>(R.layout.fragment_games), OnBackPressed {

    override val viewModel: GamesViewModel by viewModel()

    private val binding: FragmentGamesBinding by viewBinding()
    private var pagedAdapter: PagedGamesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    override fun bindVm() {
        super.bindVm()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.gamesFlow.collectLatest {
                    binding.progressIndicator.isVisible = false
                    pagedAdapter?.submitData(it)
                }
            }
        }
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
            rateButton.setOnClickListener {
                viewModel.navigateTo(MainNavigationFlow.RatingFlow, GamesFragmentDirections.actionGamesFragmentToRatingFragment())
            }
        }
    }
}