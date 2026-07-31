package com.example.learnjetpack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.learnjetpack.data.repository.UserRepository
import com.example.learnjetpack.model.UpdateProfileRequest

class OnboardingViewModel : ViewModel() {

    private val repository = UserRepository()

    var onboardingFinished by mutableStateOf(false)
        private set
    var currentStep by mutableStateOf(0)
        private set
    var displayName by mutableStateOf("")
        private set

    var heightCm by mutableStateOf("")
        private set

    var weightKg by mutableStateOf("")
        private set

    var gender by mutableStateOf("")
        private set

    var birthDate by mutableStateOf("")
        private set

    var activityLevel by mutableStateOf("")
        private set

    var goal by mutableStateOf("")
        private set


    fun updateDisplayName(value: String) {
        displayName = value
    }

    fun updateHeight(value: String){
        heightCm = value
    }

    fun updateWeight(value: String) {
        weightKg = value
    }

    fun updateGender(value: String) {
        gender = value
    }

    fun updateBirthDate(value: String) {
        birthDate = value
    }

    fun updateActivityLevel(value: String) {
        activityLevel = value
    }

    fun updateGoal(value: String) {
        goal = value
    }

    fun nextStep() {
        currentStep++
    }

    fun previousStep() {
        if (currentStep > 0) {
            currentStep--
        }
    }

    fun continueOnboarding() {
        viewModelScope.launch {
            repository.updateProfile(
                UpdateProfileRequest(
                    display_name = displayName,
                    height_cm = heightCm.toInt(),
                    sex = gender,
                    birthday = birthDate,
                    activity_level = activityLevel,
                    goal = goal
                )
            )

            repository.insertBodyMeasurement(
                weightKg.toDouble()
            )

            onboardingFinished = true
        }
    }
}

