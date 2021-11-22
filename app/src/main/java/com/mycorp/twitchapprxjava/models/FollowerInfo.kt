package com.mycorp.twitchapprxjava.models

import com.mycorp.twitchapprxjava.api.dto.gameItemDataResponse.FollowerDto
import com.mycorp.twitchapprxjava.database.entities.FollowerInfoEntity

data class FollowerInfo(val followerId: String, val followerName: String, val photoUrl: String) {

    constructor(followerInfoEntity: FollowerInfoEntity) : this(
        followerInfoEntity.id,
        followerInfoEntity.followerName,
        followerInfoEntity.photoUrl
    )

    constructor(followerDto: FollowerDto) : this(
        followerDto.user?._id!!,
        followerDto.user.display_name!!,
        followerDto.user.logo!!
    )
}