package com.example.ghii_training.domain.model.user

data class User(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val created_at: String?,
    val email: String?,
    val events_url: String?,
    val followers: Int?,
    val following: Int?,
    val id: Int,
    val location: String?,
    val login: String?,
    val name: String?,
    val public_repos: Int?,
    val repos_url: String?,
    val twitter_username: String?,
    val type: String?,
    val url: String?
)