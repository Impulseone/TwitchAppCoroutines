package com.mycorp.twitchapprxjava.presentation.fragments.favoriteGames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.R
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.databinding.FavoriteGameItemViewBinding

class FavoriteGameItemView(private val binding: FavoriteGameItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(gameData: SingleGameData?) {
        with(binding) {
            GlideApp.with(itemView.context).load(gameData?.photoUrl).into(image)
            with(itemView.context) {
                gameName.text = getString(R.string.game_name, gameData?.name)
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): FavoriteGameItemView {
            return FavoriteGameItemView(
                FavoriteGameItemViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}