package com.mycorp.twitchapprxjava.data.storage.model

import com.google.gson.annotations.SerializedName

class TwitchResponseDto(

    @field:SerializedName("top")
    val top: List<TopItem?>? = null,

    @field:SerializedName("_total")
    val total: Int? = null,
) {
    fun toListOfGameData(): List<GameData> {
        val gamesData: MutableList<GameData> = mutableListOf()
        for (item in top!!) {
            gamesData.add(
                GameData(
                    item?.game?.id!!,
                    item.game.name!!,
                    item.game.box?.large!!,
                    item.channels!!,
                    item.viewers!!
                )
            )
        }
        return gamesData
    }
}
