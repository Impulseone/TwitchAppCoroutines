package com.mycorp.twitchapprxjava.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameDataTable(
    @PrimaryKey
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int
)