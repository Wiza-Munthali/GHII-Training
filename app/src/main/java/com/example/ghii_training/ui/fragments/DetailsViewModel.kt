package com.example.ghii_training.ui.fragments

import androidx.lifecycle.ViewModel
import com.example.ghii_training.domain.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val p0: BaseRepository
): ViewModel(){
    private val userRepository = p0.p0
    private val repoRepository = p0.p1

    fun getUser(id: Int) = userRepository.getUser(id)
    fun getUserRepos(username: String) = repoRepository.queryItem(username)
}