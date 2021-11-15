package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.database.model.FavoriteGameData
import com.mycorp.twitchapprxjava.databinding.FavoriteGameItemViewBinding

class FavoriteGameViewHolder(private val binding: FavoriteGameItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameData: FavoriteGameData?) {
        with(binding) {
            GlideApp.with(itemView.context).load(gameData?.logoUrl).into(image)
            with(itemView.context) {
                gameName.text = getString(R.string.scr_favorite_game_view_holder_gameName, gameData?.name)
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