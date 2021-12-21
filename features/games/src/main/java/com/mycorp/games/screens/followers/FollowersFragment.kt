package com.mycorp.games.screens.followers

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.common.extensions.collectFlowSuspend
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.games.R
import com.mycorp.games.databinding.FragmentFollowersBinding
import com.mycorp.games.screens.followers.adapter.FollowersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragment : BaseFragment<FollowersViewModel>(R.layout.fragment_followers) {
    override val viewModel: FollowersViewModel by viewModel()
    private val binding: FragmentFollowersBinding by viewBinding()
    private var followersAdapter: FollowersAdapter? = null
    private val navArgs by navArgs<FollowersFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
        viewModel.init(navArgs.gameId)
    }

    private fun initViews() {
        with(binding) {
            followersAdapter = FollowersAdapter()
            followersRv.layoutManager =
                LinearLayoutManager(context)
            followersRv.adapter = followersAdapter
        }
    }

    override fun bindVm() {
        super.bindVm()
        collectFlowSuspend(viewModel.followersFlow) {
            binding.progressIndicator.isVisible = (it.progressIndicatorVisibility)
            followersAdapter?.submitList(it.data)
        }
    }
}