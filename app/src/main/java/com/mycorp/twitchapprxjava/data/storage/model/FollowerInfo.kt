package com.mycorp.twitchapprxjava.data.storage.model

import com.mycorp.twitchapprxjava.data.storage.model.gameItemDataResponse.FollowerDto
import com.mycorp.twitchapprxjava.data.storage.room.entities.FollowerInfoEntity

class FollowerInfo(val followerId: String, val followerName: String, val photoUrl: String) {
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