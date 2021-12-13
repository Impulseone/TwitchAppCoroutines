package com.mycorp.games.screens.games.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycorp.games.databinding.ItemGameBinding

class GameViewHolder(private val binding: ItemGameBinding, private val itemClicked: (String) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GameListItem) {
        with(binding) {
            Glide.with(itemView.context).load(item.logoUrl).into(image)
            gameName.text = item.name
            channelsCount.text = item.channels
            watchersCount.text = item.watchers
            root.setOnClickListener { itemClicked(item.id) }
        }
    }

    companion object {
        fun from(parent: ViewGroup, itemClicked: (String) -> Unit): GameViewHolder {
            return GameViewHolder(
                ItemGameBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                itemClicked
            )
        }
    }
}
