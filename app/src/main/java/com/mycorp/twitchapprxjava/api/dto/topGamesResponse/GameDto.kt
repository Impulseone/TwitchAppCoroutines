package com.mycorp.twitchapprxjava.api.dto.topGamesResponse

import com.google.gson.annotations.SerializedName

class GameDto(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("box")
    val box: BoxDto? = null,

    @field:SerializedName("_id")
    val id: Int? = null,
)