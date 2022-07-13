package com.koga.mmoapp.data.repository

import com.koga.mmoapp.data.utils.Resource
import com.koga.mmoapp.model.Game

interface GameRepository {

    suspend fun fetchGamesStream(): Resource<List<Game>>

}