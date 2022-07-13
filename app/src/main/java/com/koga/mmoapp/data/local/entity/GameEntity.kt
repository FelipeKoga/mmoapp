package com.koga.mmoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koga.mmoapp.model.Game

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
)

fun Game.toEntity() = GameEntity(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnail
)

fun GameEntity.toDomain() = Game(
    id = id,
    title = title,
    description = description,
    thumbnail = thumbnail
)