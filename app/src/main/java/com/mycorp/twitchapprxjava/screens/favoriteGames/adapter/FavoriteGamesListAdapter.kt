package com.mycorp.twitchapprxjava.screens.favoriteGames.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.mycorp.twitchapprxjava.common.helpers.BaseItemCallback
import com.mycorp.twitchapprxjava.models.FavoriteGameData
import com.mycorp.twitchapprxjava.models.ListItemData

class FavoriteGamesListAdapter :
    PagedListAdapter<ListItemData<FavoriteGameData>, FavoriteGameViewHolder>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteGameViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteGameViewHolder, position: Int) {
        holder.bind(getItem(position)?.data)
    }

}