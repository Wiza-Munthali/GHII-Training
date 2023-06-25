package com.example.ghii_training.data.local.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsert(p0: List<UserEntity>)

    @Delete
    suspend fun delete(p0: UserEntity)

    @Query("SELECT * FROM user")
    fun query(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id = :p0")
    suspend fun getUser(p0: Int): UserEntity?
}