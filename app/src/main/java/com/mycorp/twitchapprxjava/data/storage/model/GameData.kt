package com.mycorp.twitchapprxjava.data.storage.model
import kotlinx.serialization.Serializable

@Serializable
data class GameData(
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
)
