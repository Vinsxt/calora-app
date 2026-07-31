package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val display_name: String,
    val height_cm: Int,
    val gender: String,
    val birth_date: String,
    val activity_level: String,
    val goal: String
)