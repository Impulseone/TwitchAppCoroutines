package com.mycorp.favorite_games.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mycorp.common.helpers.BaseItemCallback
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.ListItemData

class FavoriteGamesListAdapter :
    PagingDataAdapter<ListItemData<FavoriteGameData>, FavoriteGameViewHolder>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteGameViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteGameViewHolder, position: Int) {
        holder.bind(getItem(position)?.data)
    }

}