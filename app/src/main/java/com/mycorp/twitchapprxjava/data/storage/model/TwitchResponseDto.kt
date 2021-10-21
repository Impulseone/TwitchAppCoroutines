package com.mycorp.twitchapprxjava.data.storage.model

import com.google.gson.annotations.SerializedName

class TwitchResponseDto(
    @field:SerializedName("top")
    val top: List<TopItem?>? = null
) {
    fun toListOfGameData(): List<GameData> {
        return top?.map {
            GameData(
                it?.game?.id!!,
                it.game.name!!,
                it.game.box?.large!!,
                it.channels!!,
                it.viewers!!
            )
        } ?: listOf()
    }
}
