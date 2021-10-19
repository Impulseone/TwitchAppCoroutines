package com.mycorp.twitchapprxjava.data.storage.model

import com.google.gson.annotations.SerializedName

data class TwitchResponse(

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

data class TopItem(

    @field:SerializedName("game")
    val game: Game? = null,

    @field:SerializedName("viewers")
    val viewers: Int? = null,

    @field:SerializedName("channels")
    val channels: Int? = null
)

data class Box(

    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("template")
    val template: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
)

data class Game(

    @field:SerializedName("giantbomb_id")
    val giantbombId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("logo")
    val logo: Logo? = null,

    @field:SerializedName("box")
    val box: Box? = null,

    @field:SerializedName("_id")
    val id: Int? = null,

    @field:SerializedName("locale")
    val locale: String? = null,

    @field:SerializedName("localized_name")
    val localizedName: String? = null
)

data class Logo(

    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("template")
    val template: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
)
