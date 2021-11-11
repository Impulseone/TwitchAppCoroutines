package com.mycorp.twitchapprxjava.screens.followers

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentFollowersListBinding
import com.mycorp.twitchapprxjava.common.fragment.BaseFragment
import com.mycorp.twitchapprxjava.screens.followers.adapter.FollowersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragment : BaseFragment<FollowersVM>(R.layout.fragment_followers_list) {

    override val viewModel: FollowersVM by viewModel()
    private val binding: FragmentFollowersListBinding by viewBinding()
    private val followersAdapter: FollowersAdapter = FollowersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
    }

    private fun initViews() {
        with(binding) {
            followersRv.layoutManager =
                LinearLayoutManager(context)
            followersRv.adapter = followersAdapter
        }
    }

    override fun bindVm() {
        super.bindVm()
        viewModel.followersLiveData().observe(viewLifecycleOwner, {
            changeProgressbarVisibility(it.progressIndicatorVisibility)
            followersAdapter.submitList(it.data)
        })
    }

    private fun changeProgressbarVisibility(visibility: Boolean) {
        binding.progressIndicator.isVisible = visibility
    }

}