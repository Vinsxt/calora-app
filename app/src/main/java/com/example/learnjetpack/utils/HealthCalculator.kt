package com.example.learnjetpack.utils

import java.util.Calendar

object HealthCalculator {

    fun calculateAge(
        birthday: String
    ): Int {

        val parts = birthday.split("-")

        val birthYear = parts[0].toInt()
        val birthMonth = parts[1].toInt()
        val birthDay = parts[2].toInt()

        val today = Calendar.getInstance()

        var age = today.get(Calendar.YEAR) - birthYear

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

        return if (sex == "Male") {

            (10 * weightKg +
                    6.25 * heightCm -
                    5 * age +
                    5).toInt()

        } else {

            (10 * weightKg +
                    6.25 * heightCm -
                    5 * age -
                    161).toInt()

        }

    }

}