package com.example.ghii_training.data.local.repo

import android.util.Log
import com.example.ghii_training.data.remote.RemoteSource
import com.example.ghii_training.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow


class RepoRepositoryImpl(
    private val api: RemoteSource
): Repository {
    override suspend fun upsert(p0: Any) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(p0: Any) {
        TODO("Not yet implemented")
    }

    override fun query(): Flow<List<RepoEntity>> {
        TODO("Not yet implemented")
    }

    override fun queryItem(p0: Any): Flow<List<RepoEntity>> {
        return flow {
            try {

                emit(api.getUserRepos(p0 as String))
            }catch (e: Exception) {
                Log.e("RepoRepositoryImpl", "queryItem: ", e)
               emit(emptyList())
            }
        }
    }

}