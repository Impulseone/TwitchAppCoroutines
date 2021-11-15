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
import com.mycorp.twitchapprxjava.common.fragment.BaseFragment
import com.mycorp.twitchapprxjava.databinding.FragmentGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment :
    BaseFragment<GameVM>(R.layout.fragment_game) {

    override val viewModel: GameVM by viewModel()
    private val binding: FragmentGameBinding by viewBinding()
    private val navArgs by navArgs<GameFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindVm()
        viewModel.init(navArgs.gameId)
    }

    private fun initViews() {
        with(binding) {
            with(viewModel) {
                like.setOnClickListener {
                    onLikeClicked()
                }
                followersCount.setOnClickListener {
                    launchFollowerScreen()
                }
            }
        }
    }

    override fun bindVm() {
        super.bindVm()
        with(binding) {
            with(viewModel) {
                bindData(gameLiveData) {
                    progressIndicator.isVisible = it.progressIndicatorVisibility
                    contentLayout.isVisible = it.data != null
                    gameName.text = it.data?.name ?: ""
                    GlideApp.with(requireContext()).load(it.data?.logoUrl).into(image)
                }
                bindData(followersIdLiveData) {
                    followersCount.text =
                        getString(R.string.scr_game_followersCount, it.size.toString())
                }
                bindData(favoriteResLiveData) {
                    like.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            it
                        )
                    )
                }
                bindCommand(launchFollowerScreenCommand) {
                    if (it != null) {
                        findNavController().navigate(
                            GameFragmentDirections.actionGameFragmentToFollowersFragment(
                                it
                            )
                        )
                    }
                }
            }
        }
    }

}