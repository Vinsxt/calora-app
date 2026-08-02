package com.example.learnjetpack.utils

import java.util.Calendar

object HealthCalculator {

    fun calculateBMR(
        sex: String,
        weightKg: Double,
        heightCm: Int,
        age: Int
    ): Int {

        val bmr =
            if (sex == "Male") {
                10 * weightKg + 6.25 * heightCm - 5 * age + 5
            } else {
                10 * weightKg + 6.25 * heightCm - 5 * age - 161
            }

        return bmr.toInt()
    }

    fun calculateTDEE(
        bmr: Int,
        activityLevel: String
    ): Int {

        val multiplier =
            when (activityLevel) {
                "Sedentary" -> 1.2
                "Lightly Active" -> 1.375
                "Moderately Active" -> 1.55
                "Very Active" -> 1.725
                "Athlete" -> 1.9
                else -> 1.2
            }

        return (bmr * multiplier).toInt()
    }

    fun calculateProtein(
        weightKg: Double,
        goal: String
    ): Int {

        val multiplier =
            when (goal) {
                "Lose Fat" -> 2.0
                "Maintain Weight" -> 1.6
                "Build Muscle" -> 2.2
                else -> 1.8
            }

        return (weightKg * multiplier).toInt()
    }

}