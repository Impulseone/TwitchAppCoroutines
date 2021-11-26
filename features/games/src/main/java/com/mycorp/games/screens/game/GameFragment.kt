package com.mycorp.games.screens.game

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.mycorp.common.fragment.BaseFragment
import com.mycorp.games.R
import com.mycorp.games.databinding.FragmentGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment :
    BaseFragment<GameViewModel>(R.layout.fragment_game) {

    override val viewModel: GameViewModel by viewModel()
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
                    gameName.text = it.data?.name ?: ""
                    Glide.with(requireContext()).load(it.data?.logoUrl).into(image)
                }
                bindData(followersCountData) {
                    followersCount.text =
                        getString(R.string.scr_game_layout_followers_tv, it)
                }
                bindData(favoriteResLiveData) {
                    like.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            it
                        )
                    )
                }
            }
        }
    }
}