package com.mycorp.twitchapprxjava

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mycorp.twitchapprxjava.databinding.GameItemViewBinding
import com.mycorp.twitchapprxjava.model.GameData

class GamesListAdapter(private val items: ArrayList<GameData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemView(
            LayoutInflater.from(parent.context).inflate(R.layout.game_item_view, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemView).bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gameData: GameData) {
            GameItemViewBinding.bind(itemView).apply {
                Glide.with(itemView.context).load(gameData.logoUrl).into(image)
                gameName.text = "com.mycorp.twitchapprxjava.model.Game: " + gameData.name
                channelsCount.text = "Channels: " + gameData.channelsCount
                watchersCount.text = "Viewers: " + gameData.watchersCount
            }
        }
    }

    fun addGames(games: ArrayList<GameData>) {
        this.items.apply {
            clear()
            addAll(games)
        }
    }
}