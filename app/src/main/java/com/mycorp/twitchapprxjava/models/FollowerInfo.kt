package com.mycorp.twitchapprxjava.models

import com.mycorp.twitchapprxjava.api.dto.gameItemDataResponse.FollowerDto
import com.mycorp.twitchapprxjava.database.entities.FollowerInfoEntity

data class FollowerInfo(val followerId: String, val followerName: String, val photoUrl: String) {
    companion object {
        fun fromFollowerInfoEntity(followerInfoEntity: FollowerInfoEntity): FollowerInfo =
            FollowerInfo(
                followerInfoEntity.id,
                followerInfoEntity.followerName,
                followerInfoEntity.photoUrl
            )

        fun fromFollowerDto(followerDto: FollowerDto) = FollowerInfo(
            followerDto.user?._id!!,
            followerDto.user.display_name!!,
            followerDto.user.logo!!
        )

    }
}