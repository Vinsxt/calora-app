package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class InsertBodyMeasurementRequest(
    val user_id: String,
    val weight_kg: Double
)