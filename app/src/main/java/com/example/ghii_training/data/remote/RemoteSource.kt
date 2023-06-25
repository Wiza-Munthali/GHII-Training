package com.example.ghii_training.data.remote

import com.example.ghii_training.data.local.repo.RepoEntity
import com.example.ghii_training.data.local.user.UserEntity
import com.example.ghii_training.domain.model.repo.Repo
import com.example.ghii_training.domain.model.user.UserListItem
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteSource {

    //Get all users
    @GET("users")
    suspend fun getUsers(): List<UserListItem>

    //Get specific user
    @GET("users/{username}")
    suspend fun getUser(@Path(value = "username", encoded = true)username: String): UserEntity

    //Get user's repositories
    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path(value = "username", encoded = true)username: String): List<RepoEntity>

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}