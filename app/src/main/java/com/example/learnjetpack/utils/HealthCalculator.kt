package com.example.learnjetpack.utils

import com.example.learnjetpack.model.DailyNutrition
import com.example.learnjetpack.model.FoodLog
import java.util.Calendar

object HealthCalculator {

    fun calculateAge(
        birthday: String
    ): Int {

        val parts = birthday.split("-")

        if (parts.size != 3) {
            return 0
        }

        val birthYear = parts[0].toInt()
        val birthMonth = parts[1].toInt()
        val birthDay = parts[2].toInt()

        val today = Calendar.getInstance()

        var age =
            today.get(Calendar.YEAR) - birthYear

        if (
            today.get(Calendar.MONTH) + 1 < birthMonth ||
            (
                    today.get(Calendar.MONTH) + 1 == birthMonth &&
                            today.get(Calendar.DAY_OF_MONTH) < birthDay
                    )
        ) {
            age--
        }

        return age
    }

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

    fun calculateDailyNutrition(
        foods: List<FoodLog>
    ): DailyNutrition {

        return DailyNutrition(
            calories = foods.sumOf { it.calories },
            protein = foods.sumOf { it.protein },
            carbs = foods.sumOf { it.carbs },
            fat = foods.sumOf { it.fat },
            fiber = foods.sumOf { it.fiber }
        )

    }

}