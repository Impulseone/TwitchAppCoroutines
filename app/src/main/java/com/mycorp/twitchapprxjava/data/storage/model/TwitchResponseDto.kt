package com.mycorp.twitchapprxjava.data.storage.model

import com.google.gson.annotations.SerializedName

class TwitchResponseDto(
    @field:SerializedName("top")
    val top: List<TopItem?>? = null
) {
    fun toListOfGameData(): List<GameData> {
        return top?.map {
            GameData(
                it?.game?.id ?: throw ConvertDtoException(),
                it.game.name ?: throw ConvertDtoException(),
                it.game.box?.large ?: throw ConvertDtoException(),
                it.channels ?: throw ConvertDtoException(),
                it.viewers ?: throw ConvertDtoException()
            )
        } ?: listOf()
    }
}

class ConvertDtoException : Exception("Dto Error converting")
