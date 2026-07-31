package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class BodyMeasurement(
    val id: String,
    val user_id: String,
    val weight_kg: Double,
    val recorded_at: String
)