package com.mycorp.twitchapprxjava.api.controllers

import com.mycorp.twitchapprxjava.api.FollowersApi

class FollowersControllerImplementation(private val followersApi: FollowersApi) :
    FollowersController {
    override fun getGameItemDataFromNetwork(id: String) = followersApi.loadGameDataItem(id)

}