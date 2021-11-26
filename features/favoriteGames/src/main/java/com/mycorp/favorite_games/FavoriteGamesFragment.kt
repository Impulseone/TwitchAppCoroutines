package com.mycorp.favorite_games

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.common.extensions.setIgnoreLastDivider
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.favorite_games.adapter.FavoriteGamesListAdapter
import com.mycorp.favorite_games.databinding.FragmentFavoriteGamesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteGamesFragment : BaseFragment<FavoriteGamesViewModel>(R.layout.fragment_favorite_games) {
    override val viewModel: FavoriteGamesViewModel by viewModel()
    private val binding: FragmentFavoriteGamesBinding by viewBinding()
    private var favoriteGamesListAdapter: FavoriteGamesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
        viewModel.init()
    }

    private fun initViews() {
        with(binding) {
            favoriteGamesListAdapter = FavoriteGamesListAdapter()
            gamesRv.apply {
                setIgnoreLastDivider(R.drawable.shape_game_divider)
                layoutManager =
                    LinearLayoutManager(context)
                adapter = favoriteGamesListAdapter
            }

        }
    }

    override fun bindVm() {
        super.bindVm()
        bindData(viewModel.gamesLiveData) {
            if (it.data != null) {
                favoriteGamesListAdapter?.submitList(it.data)
            }
        }
    }
}