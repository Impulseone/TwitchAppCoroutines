package com.mycorp.twitchapprxjava.screens.games.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

class PagedGamesAdapter(
    private val itemClicked: (Int) -> Unit
) : PagedListAdapter<GameListItem, GameViewHolder>(
    object : DiffUtil.ItemCallback<GameListItem>() {
        override fun areItemsTheSame(oldItem: GameListItem, newItem: GameListItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: GameListItem, newItem: GameListItem) = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameViewHolder.from(parent, itemClicked)

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}