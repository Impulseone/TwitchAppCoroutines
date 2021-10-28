package com.mycorp.twitchapprxjava.presentation.fragments.gamesList

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.presentation.fragments.BaseItemCallback

class GamesListAdapter :
    ListAdapter<GameData, GameItemView>(BaseItemCallback<GameData>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GameItemView.from(parent)

    override fun onBindViewHolder(holder: GameItemView, position: Int) {
        holder.bind(currentList[position])
    }


}