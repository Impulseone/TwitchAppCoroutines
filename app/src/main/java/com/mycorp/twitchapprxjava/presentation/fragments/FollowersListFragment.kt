package com.mycorp.twitchapprxjava.presentation.fragments

import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.FollowersListVM
import org.koin.androidx.viewmodel.ext.android.viewModel


const val GAME_ID = "gameId"

class FollowersListFragment: BaseFragment<FollowersListVM>(R.layout.fragment_followers_list) {
    override val viewModel: FollowersListVM by viewModel()
}