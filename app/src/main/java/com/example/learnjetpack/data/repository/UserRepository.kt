package com.example.learnjetpack.data.repository

import com.example.learnjetpack.data.remote.supabase
import com.example.learnjetpack.model.BodyMeasurement
import com.example.learnjetpack.model.CreateFoodLogRequest
import com.example.learnjetpack.model.FoodLog
import com.example.learnjetpack.model.InsertBodyMeasurementRequest
import com.example.learnjetpack.model.InsertFoodLogRequest
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import com.example.learnjetpack.model.Profile
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import com.example.learnjetpack.model.UpdateProfileRequest
import io.github.jan.supabase.postgrest.from

class UserRepository {

    suspend fun signInWithGoogle(idToken: String) {

        supabase.auth.signInWith(IDToken){
            this.idToken = idToken
            provider = Google
        }

    }

    suspend fun getCurrentProfile(): Profile? {
        if (supabase.auth.currentUserOrNull() == null) {
            return null
        }
        val user = supabase.auth.currentUserOrNull()!!

        return supabase
            .from("profiles")
            .select(
                Columns.ALL
            ) {
                filter {
                    eq("id", user.id)
                }
            }
            .decodeSingleOrNull<Profile>()
    }

    suspend fun hasProfile(): Boolean {

        return getCurrentProfile() != null

    }

    suspend fun createProfile() {

        val user = supabase.auth.currentUserOrNull()
            ?: return

        val profile = Profile(
            id = user.id
        )

        try {

            supabase
                .from("profiles")
                .insert(profile)

            println("Profile created!")

        } catch (e: Exception) {

            println("Failed to create profile")
            e.printStackTrace()

        }
    }

    fun isLoggedIn(): Boolean {

        return supabase.auth.currentSessionOrNull() != null

    }

    suspend fun isOnboardingCompleted(): Boolean {

        val profile = getCurrentProfile()
            ?: return false

        return profile.display_name != null &&
                profile.height_cm != null &&
                profile.sex != null &&
                profile.birthday != null &&
                profile.activity_level != null &&
                profile.goal != null

    }
    suspend fun updateProfile(
        request: UpdateProfileRequest
    ) {
        supabase
            .from("profiles")
            .update(request) {
                filter {
                    eq(
                        "id",
                        supabase.auth.currentUserOrNull()!!.id
                    )
                }
            }
        }

    suspend fun insertBodyMeasurement(
        weightKg: Double
    ) {

        val user =
            supabase.auth.currentUserOrNull()
                ?: return

        supabase
            .from("body_measurements")
            .insert(
                InsertBodyMeasurementRequest(
                    user_id = user.id,
                    weight_kg = weightKg
                )
            )
    }

    suspend fun getLatestBodyMeasurement(): BodyMeasurement? {

        val user =
            supabase.auth.currentUserOrNull()
                ?: return null

        return supabase
            .from("body_measurements")
            .select {
                filter {
                    eq("user_id", user.id)
                }
            }
            .decodeList<BodyMeasurement>()
            .maxByOrNull {
                it.recorded_at
            }

    }

    suspend fun insertFoodLog(
        foodLog: CreateFoodLogRequest
    ) {

        val user =
            supabase.auth.currentUserOrNull()
                ?: return

        val request =
            InsertFoodLogRequest(
                user_id = user.id,
                food_name = foodLog.food_name,
                calories = foodLog.calories,
                protein = foodLog.protein,
                carbs = foodLog.carbs,
                fat = foodLog.fat,
                fiber = foodLog.fiber,
                quantity = foodLog.quantity,
                meal_type = foodLog.meal_type
            )

        supabase
            .from("food_logs")
            .insert(request)

    }

    suspend fun getTodayFoodLogs(): List<FoodLog> {

        val user =
            supabase.auth.currentUserOrNull()
                ?: return emptyList()

        return supabase
            .from("food_logs")
            .select {
                filter {
                    eq("user_id", user.id)
                }
            }
            .decodeList<FoodLog>()
    }

    suspend fun getFoodLogs(): List<FoodLog> {

        val user =
            supabase.auth.currentUserOrNull()
                ?: return emptyList()

        return supabase
            .from("food_logs")
            .select {
                filter {
                    eq("user_id", user.id)
                }
            }
            .decodeList<FoodLog>()

    }
}