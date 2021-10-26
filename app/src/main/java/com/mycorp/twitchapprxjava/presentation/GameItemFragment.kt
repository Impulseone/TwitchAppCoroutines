package com.mycorp.twitchapprxjava.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameItemData
import com.mycorp.twitchapprxjava.presentation.viewModel.BaseFragment
import com.mycorp.twitchapprxjava.presentation.viewModel.GameItemFragmentVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mycorp.twitchapprxjava.databinding.FragmentGameItemBinding
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

const val SERIALIZED_GAME_KEY: String = "game"

class GameItemFragment : BaseFragment<GameItemFragmentVM>(R.layout.fragment_game_item) {
    override val viewModel: GameItemFragmentVM by viewModel()
    private val binding: FragmentGameItemBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVm()
    }

    private fun initViews(gameItemData: GameItemData) {
        with(binding) {
            binding.gameName.text = gameItemData.name
            GlideApp.with(requireContext()).load(gameItemData.photoUrl).into(image)
            binding.followersCount.text =
                "followers count: ${gameItemData.followersIds.size}"
        }
    }

    override fun bindVm() {
        super.bindVm()
        val gameData: GameData = Json.decodeFromString(arguments?.getString(SERIALIZED_GAME_KEY)!!)
        viewModel.gameItemLiveData().observe(viewLifecycleOwner, {
            changeContentVisibility(it.progressIndicatorVisibility)
            if (it.data != null) {
                initViews(it.data)
            }
        })
        viewModel.getFollowersListFromServer(gameData)
    }

    private fun changeContentVisibility(visibility: Boolean) {
        binding.progressIndicator.isVisible = visibility
        binding.contentLayout.isVisible = !visibility
    }

}