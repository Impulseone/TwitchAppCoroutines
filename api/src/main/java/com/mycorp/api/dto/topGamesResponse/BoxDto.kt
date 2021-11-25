package com.mycorp.api.dto.topGamesResponse

import com.google.gson.annotations.SerializedName

class BoxDto(
    @field:SerializedName("large")
    val large: String? = null,
)