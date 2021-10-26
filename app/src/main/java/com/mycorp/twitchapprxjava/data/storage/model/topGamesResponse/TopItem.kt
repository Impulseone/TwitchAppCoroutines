package com.mycorp.twitchapprxjava.data.storage.model.topGamesResponse

import com.google.gson.annotations.SerializedName

class TopItem(
    @field:SerializedName("game")
    val game: Game? = null,

    @field:SerializedName("viewers")
    val viewers: Int? = null,

    @field:SerializedName("channels")
    val channels: Int? = null
)