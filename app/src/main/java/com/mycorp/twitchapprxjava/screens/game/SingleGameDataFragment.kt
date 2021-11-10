package com.mycorp.twitchapprxjava.screens.game

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.databinding.FragmentSingleGameDataBinding
import com.mycorp.twitchapprxjava.common.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingleGameDataFragment :
    BaseFragment<SingleGameDataVM>(R.layout.fragment_single_game_data) {

    override val viewModel: SingleGameDataVM by viewModel()
    private val binding: FragmentSingleGameDataBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVm()
    }

    override fun bindVm() {
        super.bindVm()
        viewModel.singleGameLiveData().observe(viewLifecycleOwner, {
            binding.progressIndicator.isVisible = it.progressIndicatorVisibility
            if (it.data != null) {
                binding.contentLayout.isVisible = true
                setViews(it.data)
            }
        })
        viewModel.getGameById(arguments?.getString(PARCELIZE_GAME_KEY)!!)
    }

    private fun setViews(singleGameData: SingleGameData) {
        with(binding) {
            gameName.text = singleGameData.name
            GlideApp.with(requireContext()).load(singleGameData.logoUrl).into(image)
            followersCount.text =
                getString(R.string.followers_count, singleGameData.followersIds.size.toString())
            followersCount.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable(PARCELIZE_GAME_KEY, singleGameData)
                findNavController().navigate(R.id.followersListFragment, bundle)
            }
            like.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (singleGameData.isLiked) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                )
            )
            like.setOnClickListener{
                singleGameData.isLiked = !singleGameData.isLiked
                viewModel.updateSingleGameData(singleGameData)
            }
        }
    }

    companion object {
        const val PARCELIZE_GAME_KEY = "gameId"
    }

}