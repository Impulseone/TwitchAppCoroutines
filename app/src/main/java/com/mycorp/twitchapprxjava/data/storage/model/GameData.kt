package com.mycorp.twitchapprxjava.data.storage.model

class GameData(
    val id: Int,
    val name: String,
    val logoUrl: String,
    val channelsCount: Int,
    val watchersCount: Int,
){
    fun toGameDataEntity():GameDataEntity{
        return GameDataEntity(
            id,
            name,
            logoUrl,
            channelsCount,
            watchersCount
        )
    }
}
