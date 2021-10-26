package com.mycorp.twitchapprxjava.presentation.fragments.gamesListHelpers

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.data.storage.model.GameData

class GamesListAdapter :
    ListAdapter<GameData, GameItemView>(GamesListChangesCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameItemView.from(parent)

    override fun onBindViewHolder(holder: GameItemView, position: Int) {
        holder.bind(currentList[position])
    }


}