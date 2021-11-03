package com.mycorp.twitchapprxjava.database.model.topGamesResponse

import com.google.gson.annotations.SerializedName

class GameDto(
    @field:SerializedName("giantbomb_id")
    val giantbombId: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("logo")
    val logo: LogoDto? = null,

    @field:SerializedName("box")
    val box: BoxDto? = null,

    @field:SerializedName("_id")
    val id: Int? = null,

    @field:SerializedName("locale")
    val locale: String? = null,

    @field:SerializedName("localized_name")
    val localizedName: String? = null
)