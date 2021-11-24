package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.ListItemData
import com.mycorp.twitchapprxjava.common.helpers.BaseItemCallback

class FavoriteGamesListAdapter :
    PagedListAdapter<ListItemData<FavoriteGameData>, FavoriteGameViewHolder>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteGameViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteGameViewHolder, position: Int) {
        holder.bind(getItem(position)?.data)
    }

}