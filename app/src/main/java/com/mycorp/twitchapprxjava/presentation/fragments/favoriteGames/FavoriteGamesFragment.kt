package com.mycorp.twitchapprxjava.presentation.fragments.favoriteGames

import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteGamesFragment : BaseFragment<FavoriteGamesViewModel>(R.layout.fragment_favorite_games) {
    override val viewModel: FavoriteGamesViewModel by viewModel()

}