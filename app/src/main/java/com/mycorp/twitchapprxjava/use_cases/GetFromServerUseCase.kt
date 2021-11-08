package com.mycorp.twitchapprxjava.use_cases

import com.mycorp.twitchapprxjava.database.model.FollowerInfo
import com.mycorp.twitchapprxjava.database.model.GameData
import com.mycorp.twitchapprxjava.database.model.SingleGameData
import com.mycorp.twitchapprxjava.repository.Repository

class GetFromServerUseCase(private val repository: Repository) {

    fun getFollowersList(id: String) = repository.getFollowersListFromServer(id)

    fun saveFollowersToDb(followersList: List<FollowerInfo>) =
        repository.insertFollowersToDb(followersList)
    fun saveSingleGameDataToDb(singleGameData: SingleGameData) = repository.saveSingleGameDataToDb(singleGameData)
}