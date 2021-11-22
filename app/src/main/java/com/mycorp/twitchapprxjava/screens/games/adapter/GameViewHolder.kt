package com.mycorp.twitchapprxjava.screens.games.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mycorp.twitchapprxjava.GlideApp
import com.mycorp.twitchapprxjava.databinding.ItemGameBinding

class GameViewHolder(private val binding: ItemGameBinding, private val itemClicked: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GameListItem) {
        with(binding) {
            GlideApp.with(itemView.context).load(item.logoUrl).into(image)
            gameName.text = item.name
            channelsCount.text = item.channels
            watchersCount.text = item.watchers
            root.setOnClickListener { itemClicked(bindingAdapterPosition) }
        }
    }

    companion object {
        fun from(parent: ViewGroup, itemClicked: (Int) -> Unit): GameViewHolder {
            return GameViewHolder(
                ItemGameBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                itemClicked
            )
        }
    }
}
