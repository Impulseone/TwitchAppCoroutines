package com.mycorp.twitchapprxjava.domain.use_cases

import com.mycorp.twitchapprxjava.data.storage.model.GameDataTable
import com.mycorp.twitchapprxjava.domain.repository.Repository

class GetFromNetworkUseCase(private val repository: Repository) {
    fun getGames() = repository.getGamesDataFromNetwork()
    fun insertGames(gameDataTables: List<GameDataTable>) =
        repository.insertGamesDataToDb(gameDataTables)
}