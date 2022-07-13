package com.koga.mmoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.koga.mmoapp.data.local.dao.GameDao
import com.koga.mmoapp.data.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class MMODatabase : RoomDatabase() {

    abstract val gameDao: GameDao

    companion object {
        const val DATABASE_NAME = "mmoapp_database"
    }
}