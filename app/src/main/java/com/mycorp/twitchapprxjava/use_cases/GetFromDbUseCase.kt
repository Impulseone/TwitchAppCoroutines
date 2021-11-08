package com.mycorp.twitchapprxjava.use_cases

import com.mycorp.twitchapprxjava.repository.Repository

class GetFromDbUseCase(private val repository: Repository) {

    fun getGamesDataList() = repository.getGamesDataFromDb()

    fun getGameDataById(id: String) = repository.getGameDataById(id)

    fun getFollowersListByIds(followerIds: List<String>) =
        repository.getFollowersListFromDbByIds(followerIds)

    fun getSingleGameData(gameId: String) = repository.getSingleGameDataFromDb(gameId)

    fun getFavoriteGames() = repository.getFavoriteGamesFromDb()

}