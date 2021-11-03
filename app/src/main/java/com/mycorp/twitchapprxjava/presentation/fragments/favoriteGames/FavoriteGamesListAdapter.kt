package com.mycorp.twitchapprxjava.presentation.fragments.favoriteGames

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.presentation.fragments.BaseItemCallback

class FavoriteGamesListAdapter :
    PagedListAdapter<SingleGameData, FavoriteGameItemView>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        FavoriteGameItemView.from(parent)


    override fun onBindViewHolder(holder: FavoriteGameItemView, position: Int) {
        holder.bind(getItem(position))
    }

}