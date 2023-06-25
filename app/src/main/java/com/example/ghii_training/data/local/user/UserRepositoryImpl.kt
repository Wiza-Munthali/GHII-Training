package com.example.ghii_training.data.local.user

import android.util.Log
import com.example.ghii_training.data.local.Database
import com.example.ghii_training.data.remote.RemoteSource
import com.example.ghii_training.domain.repository.Repository
import com.example.ghii_training.utils.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val database: Database,
    private val api: RemoteSource
): Repository {
    private val dao = database.userDao
    override suspend fun upsert(p0: Any) {
        dao.upsert(listOf(p0 as UserEntity))
    }

    override suspend fun delete(p0: Any) {
        dao.delete(p0 as UserEntity)
    }

    override fun query(): Flow<List<UserEntity>> {
        return flow {
            if (dao.query().first().isNotEmpty()) {
                emitAll(dao.query())
            } else {
               try {
                   //Create a list of users
                   val users: MutableList<UserEntity> = mutableListOf()

                   //Get all users
                   val usersList = api.getUsers()

                   //Get profiles of each user
                   usersList.forEach {
                       val user = api.getUser(it.login)
                       users.add(user)
                   }

                   dao.upsert(users)
                   emitAll(dao.query())
               }catch (e: Exception) {
                   Log.e("UserRepositoryImpl", "query: ${e.message}")
                   emitAll(dao.query())
               }
            }
        }
    }

    override fun queryItem(p0: Any): Flow<List<*>> {
        TODO("Not yet implemented")
    }

    fun getUser(id: Int): Flow<UserEntity> {
        return flow {
            dao.getUser(id)?.let { emit(it) }
        }
    }
}