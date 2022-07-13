package com.koga.mmoapp.data.repository.impl

import com.koga.mmoapp.data.network.GameService
import com.koga.mmoapp.data.network.toDomain
import com.koga.mmoapp.data.repository.GameRepository
import com.koga.mmoapp.data.utils.Resource
import com.koga.mmoapp.model.Game
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val service: GameService
) : GameRepository {

    override suspend fun fetchGamesStream(): Resource<List<Game>> {
        return try {
            val response = service.fetchGames().map { it.toDomain() }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}