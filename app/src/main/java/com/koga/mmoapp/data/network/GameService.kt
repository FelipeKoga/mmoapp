package com.koga.mmoapp.data.network

import retrofit2.http.GET

interface GameService {

    @GET("games")
    suspend fun fetchGames(): List<GameDto>

}