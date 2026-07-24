package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(

    val id: String,

    val display_name: String? = null,

    val height_cm: Int? = null,

    val gender: String? = null,

    val birth_date: String? = null,

    val activity_level: String? = null,

    val goal: String? = null,

    val created_at: String? = null,

    val onboarding_completed: Boolean = false

)