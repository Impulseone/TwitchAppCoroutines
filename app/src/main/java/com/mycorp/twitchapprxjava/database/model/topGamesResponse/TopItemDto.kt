package com.mycorp.twitchapprxjava.database.model.topGamesResponse

import com.google.gson.annotations.SerializedName

class TopItemDto(
    @field:SerializedName("game")
    val game: GameDto? = null,

    @field:SerializedName("viewers")
    val viewers: Int? = null,

    @field:SerializedName("channels")
    val channels: Int? = null
)