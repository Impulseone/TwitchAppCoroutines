package com.mycorp.twitchapprxjava.database.model.gameItemDataResponse

data class FollowerDto(
    val created_at: String? = null,
    val user: UserDto? = null,
    val notifications: Boolean? = null
)