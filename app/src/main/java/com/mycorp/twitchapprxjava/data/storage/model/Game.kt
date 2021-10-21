package com.mycorp.twitchapprxjava.data.storage.model

import com.google.gson.annotations.SerializedName

class Game(
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