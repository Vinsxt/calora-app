package com.example.learnjetpack.data.repository

import com.example.learnjetpack.data.remote.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import com.example.learnjetpack.model.Profile
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

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

        return profile.onboarding_completed

    }
}