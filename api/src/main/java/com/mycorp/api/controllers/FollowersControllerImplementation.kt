package com.mycorp.api.controllers

import com.mycorp.api.FollowersApi
import com.mycorp.api.dto.gameItemDataResponse.GameItemDataDto

class FollowersControllerImplementation(private val followersApi: FollowersApi) :
    FollowersController {

    override fun getGameItemDataFromNetwork(id: String) = followersApi.loadGameDataItem(id)

    override suspend fun getGameItemDataFromNetworkSuspend(id: String) = followersApi.loadGameDataItemSuspend(id)

}