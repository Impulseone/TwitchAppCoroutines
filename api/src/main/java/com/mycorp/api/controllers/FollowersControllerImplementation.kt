package com.mycorp.api.controllers

import com.mycorp.api.FollowersApi

class FollowersControllerImplementation(private val followersApi: FollowersApi) :
    FollowersController {
    override fun getGameItemDataFromNetwork(id: String) = followersApi.loadGameDataItem(id)

}