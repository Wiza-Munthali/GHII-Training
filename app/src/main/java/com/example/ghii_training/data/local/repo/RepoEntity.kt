package com.example.ghii_training.data.local.repo

data class RepoEntity (
    val allow_forking: Boolean?,
    val archive_url: String?,
    val archived: Boolean?,
    val assignees_url: String?,
    val created_at: String?,
    val default_branch: String?,
    val description: String?,
    val disabled: Boolean?,
    val fork: Boolean?,
    val forks: Int?,
    val forks_count: Int?,
    val full_name: String?,
    val id: Int?,
    val language: String?,
    val name: String?,
    val open_issues: Int?,
    val open_issues_count: Int?,
    val pushed_at: String?,
    val size: Int?,
    val updated_at: String?,
    val url: String?,
    val visibility: String?,
    val watchers: Int?,
    val watchers_count: Int?,
    val stargazers_count: Int?,
)