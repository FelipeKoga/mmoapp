package com.koga.mmoapp.data.network

import com.google.gson.annotations.SerializedName
import com.koga.mmoapp.model.Game

data class GameDto(
    val id: Int,
    val title: String,
    @SerializedName("short_description")
    val description: String,
    val thumbnail: String,
)

fun GameDto.toDomain() = Game(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnail
)