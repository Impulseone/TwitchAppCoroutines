package com.mycorp.api.dto.topGamesResponse

import com.google.gson.annotations.SerializedName
import com.mycorp.api.dto.ConvertDtoException
import com.mycorp.model.GameData

class TopGamesResponseDto(
    @field:SerializedName("top")
    val top: List<TopItemDto?>? = null
) {
    fun toModel(): List<GameData> {
        return top?.map {
            GameData(
                it?.game?.id?.toString() ?: throw ConvertDtoException(),
                it.game.name ?: throw ConvertDtoException(),
                it.game.box?.large ?: throw ConvertDtoException(),
                it.channels ?: throw ConvertDtoException(),
                it.viewers ?: throw ConvertDtoException()
            )
        } ?: listOf()
    }
}
