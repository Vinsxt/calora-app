package com.example.learnjetpack.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(

    val id: String,

    val display_name: String?,

    val height_cm: Int?,

    val weight_kg: Double?,

    val gender: String?,

    val birth_date: String?,

    val activity_level: String?,

    val goal: String?,

    val created_at: String?

)