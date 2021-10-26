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
import com.mycorp.twitchapprxjava.presentation.fragments.followersList.GAME_ID
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.SingleGameDataFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

const val SERIALIZED_GAME_KEY = "game"

class SingleGameDataFragment : BaseFragment<SingleGameDataFragmentVM>(R.layout.fragment_single_game_data) {
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
            binding.followersCount.text = getString(R.string.followers_count, singleGameData.followersIds.size.toString())
            binding.followersCount.setOnClickListener{
                val bundle = Bundle()
                bundle.putString(GAME_ID, Json.encodeToString(singleGameData))
                findNavController().navigate(R.id.followersListFragment, bundle)
            }
        }
    }

    override fun bindVm() {
        super.bindVm()
        val gameData: GameData = Json.decodeFromString(arguments?.getString(SERIALIZED_GAME_KEY)!!)
        viewModel.gameItemLiveData().observe(viewLifecycleOwner, {
            if (it.data == null) {
                when (it.progressIndicatorVisibility) {
                    true -> changeContentVisibility(
                        indicatorVisibility = true,
                        contentVisibility = false
                    )
                    false -> changeContentVisibility(
                        indicatorVisibility = false,
                        contentVisibility = false
                    )
                }
            } else {
                changeContentVisibility(indicatorVisibility = false, contentVisibility = true)
            }
            if (it.data != null) {
                initViews(it.data)
            }
        })
        viewModel.getFollowersListFromServer(gameData)
    }

    private fun changeContentVisibility(indicatorVisibility: Boolean, contentVisibility: Boolean) {
        binding.progressIndicator.isVisible = indicatorVisibility
        binding.contentLayout.isVisible = contentVisibility
    }

}