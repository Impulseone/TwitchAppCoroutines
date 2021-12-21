package com.mycorp.api.controllers

import com.mycorp.api.FollowersApi

class FollowersControllerImplementation(private val followersApi: FollowersApi) :
    FollowersController {

    override suspend fun getGameItemDataFromNetwork(id: String) = followersApi.loadGameDataItem(id)

}