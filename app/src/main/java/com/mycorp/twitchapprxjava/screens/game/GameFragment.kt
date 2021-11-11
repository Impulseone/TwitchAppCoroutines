package com.mycorp.twitchapprxjava.screens.game

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.databinding.FragmentSingleGameDataBinding
import com.mycorp.twitchapprxjava.common.fragment.BaseFragment
import com.mycorp.twitchapprxjava.screens.games.GamesFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment :
    BaseFragment<GameVM>(R.layout.fragment_single_game_data) {

    override val viewModel: GameVM by viewModel()
    private val binding: FragmentSingleGameDataBinding by viewBinding()
    private val navArgs by navArgs<GameFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
        viewModel.init(navArgs.gameId)
    }

    override fun bindVm() {
        super.bindVm()
        with(binding) {
            bindData(viewModel.singleGameLiveData()) {
                progressIndicator.isVisible = it.progressIndicatorVisibility
                contentLayout.isVisible = it.data != null
                gameName.text = it.data?.name ?: ""
                GlideApp.with(requireContext()).load(it.data?.logoUrl).into(image)
            }

            bindData(viewModel.followersIdLiveData()) {
                followersCount.text =
                    getString(R.string.followers_count, it.size.toString())
            }

            bindData(viewModel.favoriteStateLiveData()) {
                like.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        if (it) R.drawable.like_filled_icon else R.drawable.like_outlined_icon
                    )
                )
            }
            viewModel.launchFollowerScreenCommand.observe(viewLifecycleOwner, {
                navigateToFollowersFragment(viewModel.singleGameLiveData().value?.data?.id!!)
            })
        }
    }

    private fun initViews() {
        with(binding) {
            like.setOnClickListener {
                viewModel.onLikeClicked(viewModel.singleGameLiveData().value?.data!!)
            }
            followersCount.setOnClickListener {
                viewModel.launchFollowerScreen(viewModel.singleGameLiveData().value?.data?.id!!)
            }
        }
    }

    private fun navigateToFollowersFragment(gameId: String) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToFollowersFragment(
                gameId
            )
        )
    }

}