package com.mycorp.twitchapprxjava.data.repository

import com.mycorp.twitchapprxjava.data.network.NetworkController
import com.mycorp.twitchapprxjava.data.storage.Storage
import com.mycorp.twitchapprxjava.data.storage.model.FollowerInfo
import com.mycorp.twitchapprxjava.data.storage.model.GameData
import com.mycorp.twitchapprxjava.data.storage.model.SingleGameData
import com.mycorp.twitchapprxjava.domain.repository.Repository
import io.reactivex.Single

class RepositoryImplementation(
    private val networkController: NetworkController,
    private val storage: Storage
) : Repository {

    override fun getGamesDataFromServer() =
        networkController.getDataFromNetwork().map {
            it.toListOfGameData()
        }

    override fun getFollowersListFromServer(id: String): Single<List<FollowerInfo>> =
        networkController.getGameItemDataFromNetwork(id).map {
            it.follows?.map { FollowerInfo.fromFollowerDto(it!!) }
        }

    override fun getGamesDataFromDb() = storage.getGamesDataFromDb().map {
        it.map { GameData.fromEntity(it) }
    }

    override fun getFollowersListFromDbByIds(followerIds: List<String>) =
        storage.getFollowersFromDbByIds(followerIds)
            .map { it.map { FollowerInfo.fromFollowerInfoEntity(it) } }

    override fun getSingleGameDataFromDb(gameId: String) =
        storage.getGameItemData(gameId).map { SingleGameData.fromGameItemDataEntity(it) }

    override fun getFavoriteGamesFromDb() = storage.getFavoriteGamesFromDb()
        .map {
            SingleGameData.fromGameItemDataEntity(it)
        }

    override fun insertGamesDataToDb(gameDataEntities: List<GameData>) =
        storage.insertGamesData(gamesData = gameDataEntities)

    override fun insertFollowersToDb(followersList: List<FollowerInfo>) =
        storage.insertFollowersData(followersList)

    override fun saveSingleGameDataToDb(singleGameData: SingleGameData) =
        storage.saveSingleGameData(singleGameData)


}