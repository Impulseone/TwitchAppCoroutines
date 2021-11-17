package com.mycorp.twitchapprxjava.api.dto.topGamesResponse

import com.google.gson.annotations.SerializedName

class LogoDto(
    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("template")
    val template: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
)