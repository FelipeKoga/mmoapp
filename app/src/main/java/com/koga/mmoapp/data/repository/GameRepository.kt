package com.koga.mmoapp.data.repository

import com.koga.mmoapp.data.utils.Resource
import com.koga.mmoapp.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun fetchGamesStream(): Flow<Resource<List<Game>>>

}