package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateFoodLogRequest(
    val food_name: String,
    val calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val fiber: Double,
    val quantity: Double,
    val meal_type: String
)