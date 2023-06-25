package com.example.ghii_training.domain.repository

import com.example.ghii_training.data.local.repo.RepoRepositoryImpl
import com.example.ghii_training.data.local.user.UserRepositoryImpl

data class BaseRepository(
    val p0: UserRepositoryImpl,
    val p1: RepoRepositoryImpl
)