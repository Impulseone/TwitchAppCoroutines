package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.models.FavoriteGameData
import com.mycorp.twitchapprxjava.databinding.ItemFavoriteGameBinding

class FavoriteGameViewHolder(private val binding: ItemFavoriteGameBinding) :
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
                ItemFavoriteGameBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}