package com.mycorp.twitchapprxjava.presentation.fragments.gamesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.databinding.GameItemViewBinding

class GameItemView(private val binding: GameItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameData: GameData) {
        with(binding) {
            GlideApp.with(itemView.context).load(gameData.logoUrl).into(image)
            with(itemView.context) {
                gameName.text = getString(R.string.game_name, gameData.name)
                channelsCount.text = getString(R.string.channels, gameData.channelsCount.toString())
                watchersCount.text = getString(R.string.watchers, gameData.watchersCount.toString())
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): GameItemView {
            return GameItemView(
                GameItemViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}
