package com.example.learnjetpack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {
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
        when (currentStep) {
            0, 1 -> {
                nextStep()
            }
            2 -> {
                // Save profile later
            }
        }
    }
}

