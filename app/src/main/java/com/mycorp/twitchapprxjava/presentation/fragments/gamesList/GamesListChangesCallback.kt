package com.mycorp.twitchapprxjava.presentation.fragments.gamesList

import androidx.recyclerview.widget.DiffUtil
import com.mycorp.twitchapprxjava.data.storage.model.GameData

class GamesListChangesCallback :
    DiffUtil.ItemCallback<GameData>() {

    override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return oldItem == newItem
    }
}