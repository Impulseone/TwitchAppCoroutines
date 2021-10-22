package com.mycorp.twitchapprxjava.presentation

import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.GameItemFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameItemFragment : BaseFragment<GameItemFragmentVM>(R.layout.fragment_game_item) {
    override val viewModel: GameItemFragmentVM by viewModel()


}