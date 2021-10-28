package com.mycorp.twitchapprxjava.presentation.fragments.favoriteGames

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.presentation.fragments.BaseItemCallback

class FavoriteGamesListAdapter :
    PagingDataAdapter<SingleGameData, FavoriteGameItemView>(BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        FavoriteGameItemView.from(parent)


    override fun onBindViewHolder(holder: FavoriteGameItemView, position: Int) {
        holder.bind(getItem(position))
    }

}