package com.example.ghii_training.ui.fragments

import androidx.lifecycle.ViewModel
import com.example.ghii_training.domain.repository.BaseRepository
import com.example.ghii_training.utils.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val p0: BaseRepository
) : ViewModel(){
    private val repository = p0.p0

    val query = repository.query().map {
        it.map{entity ->
            entity.toUser()
        }
    }
}