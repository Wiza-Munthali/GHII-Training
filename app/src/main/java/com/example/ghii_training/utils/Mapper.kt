package com.example.ghii_training.utils

import com.example.ghii_training.data.local.repo.RepoEntity
import com.example.ghii_training.data.local.user.UserEntity
import com.example.ghii_training.domain.model.repo.Repo
import com.example.ghii_training.domain.model.user.User

fun UserEntity.toUser() = User(
    avatar_url = avatar_url,
    bio = bio,
    blog = blog,
    company = company,
    created_at = created_at,
    email = email,
    events_url = events_url,
    followers = followers,
    following = following,
    id = id,
    location = location,
    login = login,
    name = name,
    public_repos = public_repos,
    repos_url = repos_url,
    twitter_username = twitter_username,
    type = type,
    url = url
)

fun RepoEntity.toRepo() = Repo(
    allow_forking = allow_forking,
    archive_url = archive_url,
    archived = archived,
    assignees_url = assignees_url,
    created_at = created_at,
    default_branch = default_branch,
    description = description,
disabled = disabled,
fork = fork,
forks = forks,
forks_count = forks_count,
full_name = full_name,
id = id,
language = language,
name = name,
open_issues = open_issues,
open_issues_count = open_issues_count,
pushed_at = pushed_at,
size = size,
updated_at = updated_at,
url = url,
visibility = visibility,
watchers = watchers,
watchers_count = watchers_count,
    stargazers_count = stargazers_count,
)