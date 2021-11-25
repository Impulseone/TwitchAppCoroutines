package com.mycorp.api.dto.gameItemDataResponse

import com.mycorp.api.dto.ConvertDtoException
import com.mycorp.model.FollowerInfo

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