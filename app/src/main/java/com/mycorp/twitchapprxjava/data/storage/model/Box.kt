package com.mycorp.twitchapprxjava.data.storage.model

import com.google.gson.annotations.SerializedName

class Box(
    @field:SerializedName("small")
    val small: String? = null,

    @field:SerializedName("template")
    val template: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
)