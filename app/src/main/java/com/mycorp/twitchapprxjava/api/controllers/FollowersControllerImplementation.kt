package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.FollowersApi
import com.mycorp.twitchapprxjava.api.dto.gameItemDataResponse.GameItemDataDto
import io.reactivex.Single

class FollowersControllerImplementation(private val followersApi: FollowersApi) :
    FollowersController {
    override fun getGameItemDataFromNetwork(id: String) = followersApi.loadGameDataItem(id)

}