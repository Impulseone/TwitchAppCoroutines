package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.databinding.FavoriteGameItemViewBinding

class FavoriteGameViewHolder(private val binding: FavoriteGameItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameData: SingleGameData?) {
        with(binding) {
            GlideApp.with(itemView.context).load(gameData?.logoUrl).into(image)
            with(itemView.context) {
                gameName.text = getString(R.string.game_name, gameData?.name)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): FavoriteGameViewHolder {
            return FavoriteGameViewHolder(
                FavoriteGameItemViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}