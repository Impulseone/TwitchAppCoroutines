package com.mycorp.games.screens.games

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.common.extensions.collectFlowSuspend
import com.mycorp.common.extensions.setIgnoreLastDivider
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.games.R
import com.mycorp.games.databinding.FragmentGamesBinding
import com.mycorp.games.screens.games.adapter.GamesSourceType
import com.mycorp.games.screens.games.adapter.PagingGamesAdapter
import com.mycorp.navigation.MainNavigationFlow
import com.mycorp.navigation.OnBackPressed
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalPagingApi::class)
class GamesFragment : BaseFragment<GamesViewModel>(R.layout.fragment_games), OnBackPressed {

    override val viewModel: GamesViewModel by viewModel()

    private val binding: FragmentGamesBinding by viewBinding()
    private var pagingAdapter: PagingGamesAdapter? = null

    private var dbJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    override fun bindVm() {
        super.bindVm()
        collectFlowSuspend(viewModel.getFlow(GamesSourceType.SERVER)) {
            binding.progressIndicator.isVisible = false
            pagingAdapter?.submitData(it)
        }
    }

    private fun initViews() {
        with(binding) {
            pagingAdapter = PagingGamesAdapter {
                viewModel.gameItemClicked(it)
            }
            pagingAdapter!!.addLoadStateListener {
                if (it.refresh is LoadState.Error) {
                    dbJob?.cancel()
                    dbJob = collectFlowSuspend(viewModel.getFlow(GamesSourceType.DATABASE)){
                            binding.progressIndicator.isVisible = false
                            pagingAdapter?.submitData(it)
                        }
                }
            }
            gamesRv.apply {
                adapter = pagingAdapter!!
                setIgnoreLastDivider(R.drawable.shape_game_divider)
            }
            rateButton.setOnClickListener {
                viewModel.navigateTo(
                    MainNavigationFlow.RatingFlow,
                    GamesFragmentDirections.actionGamesFragmentToRatingFragment()
                )
            }
        }
    }
}