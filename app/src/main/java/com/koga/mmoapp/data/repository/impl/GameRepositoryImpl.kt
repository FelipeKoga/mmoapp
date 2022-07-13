package com.koga.mmoapp.data.repository.impl

import com.koga.mmoapp.data.local.dao.GameDao
import com.koga.mmoapp.data.local.entity.toDomain
import com.koga.mmoapp.data.local.entity.toEntity
import com.koga.mmoapp.data.network.GameService
import com.koga.mmoapp.data.network.toDomain
import com.koga.mmoapp.data.repository.GameRepository
import com.koga.mmoapp.data.utils.Resource
import com.koga.mmoapp.data.utils.networkBoundResource
import com.koga.mmoapp.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val service: GameService,
    private val dao: GameDao
) : GameRepository {

    override fun fetchGamesStream(): Flow<Resource<List<Game>>> {
        return networkBoundResource(
            query = { dao.fetchGamesStream().map { entities -> entities.map { it.toDomain() } } },
            fetch = { service.fetchGames().map { it.toDomain() } },
            saveFetchResult = { items ->
                dao.insertAll(items.map { it.toEntity() })
            }
        )
    }

}