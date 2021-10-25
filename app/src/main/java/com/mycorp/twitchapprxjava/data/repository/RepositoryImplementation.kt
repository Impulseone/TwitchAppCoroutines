package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.GameItemDataDto
import com.mycorp.twitchapprxjava.domain.repository.Repository
import io.reactivex.Single

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromNetwork() =
        networkController.getDataFromNetwork().map {
            it.toListOfGameData()
        }

    override fun getFollowersList(id: String): Single<List<FollowerInfo>> =
        networkController.getGameItemDataFromNetwork(id).map {
            it.follows?.map { FollowerInfo.fromFollowerDto(it!!) }
        }

    override fun getGamesDataFromDb() = storage.getGamesDataFromDb().map {
        it.map { GameData.fromEntity(it) }
    }


    override fun insertGamesDataToDb(gameDataTables: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataTables)
}