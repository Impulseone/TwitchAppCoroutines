package com.mycorp.twitchapprxjava.presentation.fragments.followersList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentFollowersListBinding
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.FollowersListVM
import org.koin.androidx.viewmodel.ext.android.viewModel

const val GAME_ID = "gameId"

class FollowersListFragment : BaseFragment<FollowersListVM>(R.layout.fragment_followers_list) {

    override val viewModel: FollowersListVM by viewModel()
    private val binding: FragmentFollowersListBinding by viewBinding()
    private val followersListAdapter: FollowersListAdapter = FollowersListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    private fun initViews() {
        with(binding) {
            followersRv.layoutManager =
                LinearLayoutManager(context)
            followersRv.adapter = followersListAdapter
        }
    }

    override fun bindVm() {
        super.bindVm()
        viewModel.followersLiveData().observe(viewLifecycleOwner, {
            changeProgressbarVisibility(it.progressIndicatorVisibility)
            followersListAdapter.submitList(it.data)
        })
        viewModel.getFollowersFromServer(arguments?.getParcelable(GAME_ID)!!)
    }

    private fun changeProgressbarVisibility(visibility: Boolean) {
        binding.progressIndicator.isVisible = visibility
    }

}