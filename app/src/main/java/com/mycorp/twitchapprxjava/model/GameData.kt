package com.mycorp.twitchapprxjava.model

data class GameData(
    val id:Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
)
