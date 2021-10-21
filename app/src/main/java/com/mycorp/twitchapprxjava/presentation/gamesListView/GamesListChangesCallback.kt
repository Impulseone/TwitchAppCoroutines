package com.mycorp.twitchapprxjava.presentation.gamesListView

import androidx.recyclerview.widget.DiffUtil
import com.mycorp.twitchapprxjava.data.storage.model.GameData

class GamesListChangesCallback :
    DiffUtil.ItemCallback<GameData>() {

    override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return (oldItem.name == newItem.name &&
                oldItem.channelsCount == newItem.channelsCount &&
                oldItem.logoUrl == newItem.logoUrl &&
                oldItem.watchersCount == newItem.watchersCount)
    }
}