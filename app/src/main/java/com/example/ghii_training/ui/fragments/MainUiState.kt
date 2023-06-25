package com.example.ghii_training.ui.fragments

import com.example.ghii_training.domain.model.user.User

data class MainUiState(
    val users : List<User> = emptyList(),
)