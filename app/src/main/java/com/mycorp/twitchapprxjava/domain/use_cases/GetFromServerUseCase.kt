package com.mycorp.twitchapprxjava.domain.use_cases

import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.repository.Repository

class GetFromServerUseCase(private val repository: Repository) {

    fun getGames() = repository.getGamesDataFromServer()
    fun getFollowersList(id: String) = repository.getFollowersListFromServer(id)

    fun saveGamesToDb(gameData: List<GameData>) =
        repository.insertGamesDataToDb(gameData)
    fun saveFollowersToDb(followersList: List<FollowerInfo>) =
        repository.insertFollowersToDb(followersList)
    fun saveSingleGameDataToDb(singleGameData: SingleGameData) = repository.saveSingleGameDataToDb(singleGameData)
}