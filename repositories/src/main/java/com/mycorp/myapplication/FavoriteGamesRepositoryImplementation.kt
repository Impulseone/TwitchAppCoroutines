package com.mycorp.myapplication

import androidx.paging.PagingSource
import com.mycorp.database.entities.FavoriteGameDataEntity
import com.mycorp.database.storage.FavoriteGamesStorage
import com.mycorp.model.FavoriteGameData
import com.mycorp.model.GameData
import com.mycorp.model.ListItemData
import io.reactivex.Single

class FavoriteGamesRepositoryImplementation(
    private val favoriteGamesStorage: FavoriteGamesStorage
) : FavoriteGamesRepository {

    override fun getFavoriteGamesList(): PagingSource<Int, FavoriteGameDataEntity> {
       return favoriteGamesStorage.getFavoriteGamesList()
    }

    override fun checkIsFavorite(gameId: String) = favoriteGamesStorage.checkIsFavorite(gameId)

    override fun insertFavoriteGame(gameData: GameData) = favoriteGamesStorage.insertFavoriteGame(gameData)

    override fun deleteByGameId(gameId: String) = favoriteGamesStorage.deleteFavoriteByGameId(gameId)

}