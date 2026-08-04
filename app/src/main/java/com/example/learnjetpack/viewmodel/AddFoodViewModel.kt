package com.example.learnjetpack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnjetpack.data.repository.UserRepository
import com.example.learnjetpack.model.CreateFoodLogRequest
import kotlinx.coroutines.launch

class AddFoodViewModel : ViewModel() {

    private val repository = UserRepository()

    var foodName by mutableStateOf("")
        private set

    var calories by mutableStateOf("")
        private set

    var protein by mutableStateOf("")
        private set

    var carbs by mutableStateOf("")
        private set

    var fat by mutableStateOf("")
        private set

    var fiber by mutableStateOf("")
        private set

    var quantity by mutableStateOf("")
        private set

    var mealType by mutableStateOf("Lunch")
        private set

    var saveSuccess by mutableStateOf(false)
        private set

    fun updateFoodName(value: String) {
        foodName = value
    }

    fun updateCalories(value: String) {
        calories = value
    }

    fun updateProtein(value: String) {
        protein = value
    }

    fun updateCarbs(value: String) {
        carbs = value
    }

    fun updateFat(value: String) {
        fat = value
    }

    fun updateFiber(value: String) {
        fiber = value
    }

    fun updateQuantity(value: String) {
        quantity = value
    }

    fun updateMealType(value: String) {
        mealType = value
    }

    fun saveFood() {

        viewModelScope.launch {

            repository.insertFoodLog(
                CreateFoodLogRequest(
                    food_name = foodName,
                    calories = calories.toDouble(),
                    protein = protein.toDouble(),
                    carbs = carbs.toDouble(),
                    fat = fat.toDouble(),
                    fiber = fiber.toDouble(),
                    quantity = quantity.toDouble(),
                    meal_type = mealType
                )
            )

            saveSuccess = true

        }

    }

}