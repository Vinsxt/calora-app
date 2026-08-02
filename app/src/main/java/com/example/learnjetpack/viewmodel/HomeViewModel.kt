package com.example.learnjetpack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnjetpack.data.repository.UserRepository
import com.example.learnjetpack.model.Profile
import kotlinx.coroutines.launch
import com.example.learnjetpack.model.HealthMetrics

class HomeViewModel : ViewModel() {

    private val repository = UserRepository()

    var metrics by mutableStateOf<HealthMetrics?>(null)
        private set

    var profile by mutableStateOf<Profile?>(null)
        private set

    var isLoading by mutableStateOf(true)
        private set

    fun loadProfile() {
        viewModelScope.launch {
            profile = repository.getCurrentProfile()
            isLoading = false
        }
    }
}