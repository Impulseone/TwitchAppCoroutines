package com.mycorp.twitchapprxjava.use_cases

import com.mycorp.twitchapprxjava.repository.Repository

class GetFromDbUseCase(private val repository: Repository) {

    fun getGamesData() = repository.getGamesDataFromDb()

    fun getFollowersListByIds(followerIds: List<String>) =
        repository.getFollowersListFromDbByIds(followerIds)

    fun getSingleGameData(gameId: String) = repository.getSingleGameDataFromDb(gameId)

    fun getFavoriteGames() = repository.getFavoriteGamesFromDb()

}