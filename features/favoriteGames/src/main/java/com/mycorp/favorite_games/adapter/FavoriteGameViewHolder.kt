package com.mycorp.favorite_games.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycorp.favorite_games.R
import com.mycorp.favorite_games.databinding.ItemFavoriteGameBinding
import com.mycorp.model.FavoriteGameData

class FavoriteGameViewHolder(private val binding: ItemFavoriteGameBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameData: FavoriteGameData?) {
        with(binding) {
            Glide.with(itemView.context).load(gameData?.logoUrl).into(image)
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