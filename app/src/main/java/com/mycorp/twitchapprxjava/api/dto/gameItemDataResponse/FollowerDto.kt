package com.mycorp.twitchapprxjava.api.dto.gameItemDataResponse

import com.mycorp.model.FollowerInfo
import com.mycorp.twitchapprxjava.api.dto.ConvertDtoException

data class FollowerDto(
    val created_at: String? = null,
    val user: UserDto? = null,
    val notifications: Boolean? = null
){
    fun toModel() = FollowerInfo(
        user?._id?: throw ConvertDtoException(),
        user.display_name?: throw ConvertDtoException(),
        user.logo?: throw ConvertDtoException(),
    )
}