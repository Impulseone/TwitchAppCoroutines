package com.mycorp.twitchapprxjava.domain.use_cases

import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.domain.repository.Repository

class GetFromServerUseCase(private val repository: Repository) {
    fun getGames() = repository.getGamesDataFromNetwork()
    fun getFollowersList(id: String) = repository.getFollowersList(id)
    fun insertGames(gameData: List<GameData>) =
        repository.insertGamesDataToDb(gameData)
}