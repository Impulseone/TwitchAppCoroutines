package com.mycorp.twitchapprxjava.usecases

import com.mycorp.model.GameData
import com.mycorp.twitchapprxjava.repository.FavoriteGamesRepository

class FavoriteGamesUseCaseImpl(private val favoriteGamesRepository: FavoriteGamesRepository) :
    FavoriteGamesUseCase {
    override fun saveGame(gameData: GameData) = favoriteGamesRepository.insertFavoriteGame(gameData)

    override fun deleteGameById(id: String) = favoriteGamesRepository.deleteByGameId(id)

}