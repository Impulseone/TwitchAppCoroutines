package com.mycorp.games.screens.followers

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.games.R
import com.mycorp.games.databinding.FragmentFollowersBinding
import com.mycorp.games.screens.followers.adapter.FollowersAdapter
import com.mycorp.navigation.BaseNavigationFlow
import com.mycorp.navigation.MainNavigationFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
//        bindData(viewModel.followersLiveData()) {
//            binding.progressIndicator.isVisible = (it.progressIndicatorVisibility)
//            followersAdapter?.submitList(it.data)
//        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.followersFlow.collectLatest {
                    binding.progressIndicator.isVisible = (it.progressIndicatorVisibility)
                    followersAdapter?.submitList(it.data)
                }
            }
        }

    }
}