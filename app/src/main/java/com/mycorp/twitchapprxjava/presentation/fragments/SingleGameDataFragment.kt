package com.mycorp.twitchapprxjava.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.databinding.FragmentSingleGameDataBinding
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.SingleGameDataFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingleGameDataFragment :
    BaseFragment<SingleGameDataFragmentVM>(R.layout.fragment_single_game_data) {
    override val viewModel: SingleGameDataFragmentVM by viewModel()
    private val binding: FragmentSingleGameDataBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVm()
    }

    private fun initViews(singleGameData: SingleGameData) {
        with(binding) {
            binding.gameName.text = singleGameData.name
            GlideApp.with(requireContext()).load(singleGameData.photoUrl).into(image)
            binding.followersCount.text =
                getString(R.string.followers_count, singleGameData.followersIds.size.toString())
            binding.followersCount.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable(PARCELIZE_GAME_KEY, singleGameData)
                findNavController().navigate(R.id.followersListFragment, bundle)
            }
        }
    }

    override fun bindVm() {
        super.bindVm()
        val gameData: GameData = arguments?.getParcelable(PARCELIZE_GAME_KEY)!!
        viewModel.gameItemLiveData().observe(viewLifecycleOwner, {
            binding.progressIndicator.isVisible = it.progressIndicatorVisibility
            if (it.data != null) {
                binding.contentLayout.isVisible = true
                initViews(it.data)
            }
        })
        viewModel.getFollowersListFromServer(gameData)
    }

    companion object {
        const val PARCELIZE_GAME_KEY = "game"
    }

}