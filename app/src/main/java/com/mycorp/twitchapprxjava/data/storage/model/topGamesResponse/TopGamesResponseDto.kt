package com.mycorp.twitchapprxjava.data.storage.model.topGamesResponse

import com.google.gson.annotations.SerializedName
import com.mycorp.twitchapprxjava.data.storage.model.GameData

class TopGamesResponseDto(
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
