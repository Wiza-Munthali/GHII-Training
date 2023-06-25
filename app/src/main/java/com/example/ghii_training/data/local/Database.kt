package com.example.ghii_training.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ghii_training.data.local.user.UserDao
import com.example.ghii_training.data.local.user.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "ghii_training.db"
    }
}