package com.example.ghii_training.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    //function to insert and edit
    suspend fun upsert(p0: Any)

    //function to delete specific item
    suspend fun delete(p0: Any)

    //function to get all users
    fun query(): Flow<List<*>>

    //function to get specific item
    fun queryItem(p0: Any): Flow<List<*>>
}