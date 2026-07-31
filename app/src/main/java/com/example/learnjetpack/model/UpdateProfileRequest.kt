package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val display_name: String,
    val height_cm: Int,
    val sex: String,
    val birthday: String,
    val activity_level: String,
    val goal: String
)