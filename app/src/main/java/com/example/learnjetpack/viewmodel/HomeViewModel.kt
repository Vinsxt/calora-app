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
import com.example.learnjetpack.model.BodyMeasurement
import com.example.learnjetpack.model.FoodLog
import com.example.learnjetpack.utils.HealthCalculator
import com.example.learnjetpack.model.DailyNutrition

class HomeViewModel : ViewModel() {

    var latestWeight by mutableStateOf<BodyMeasurement?>(null)
        private set
    private val repository = UserRepository()

    var metrics by mutableStateOf<HealthMetrics?>(null)
        private set

    var profile by mutableStateOf<Profile?>(null)
        private set

    var isLoading by mutableStateOf(true)
        private set

    var foodLogs by mutableStateOf<List<FoodLog>>(emptyList())
        private set

    var dailyNutrition by mutableStateOf<DailyNutrition?>(null)
        private set

    fun loadProfile() {
        viewModelScope.launch {

            val profile = repository.getCurrentProfile() ?: run {
                isLoading = false
                return@launch
            }

            val weight = repository.getLatestBodyMeasurement() ?: run {
                isLoading = false
                return@launch
            }

            this@HomeViewModel.profile = profile
            latestWeight = weight

            foodLogs =
                repository.getFoodLogs()

            val birthday = profile.birthday ?: run {
                isLoading = false
                return@launch
            }

            val sex = profile.sex ?: run {
                isLoading = false
                return@launch
            }

            val height = profile.height_cm ?: run {
                isLoading = false
                return@launch
            }

            val activity = profile.activity_level ?: run {
                isLoading = false
                return@launch
            }

            val goal = profile.goal ?: run {
                isLoading = false
                return@launch
            }

            val age = HealthCalculator.calculateAge(birthday)

            val bmr = HealthCalculator.calculateBMR(
                sex = sex,
                weightKg = weight.weight_kg,
                heightCm = height,
                age = age
            )

            val tdee = HealthCalculator.calculateTDEE(
                bmr = bmr,
                activityLevel = activity
            )

            val protein = HealthCalculator.calculateProtein(
                weightKg = weight.weight_kg,
                goal = goal
            )

            metrics = HealthMetrics(
                age = age,
                bmr = bmr,
                tdee = tdee,
                proteinGoal = protein
            )

            dailyNutrition =
                HealthCalculator.calculateDailyNutrition(
                    foodLogs
                )

            isLoading = false
        }
    }

    fun deleteFood(
        id: String
    ) {

        viewModelScope.launch {

            repository.deleteFoodLog(id)

            loadProfile()

        }

    }
}