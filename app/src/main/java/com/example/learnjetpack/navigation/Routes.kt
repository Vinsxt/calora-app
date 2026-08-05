package com.example.learnjetpack.navigation

object Routes {

    const val LOGIN = "login"
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val ADD_FOOD = "add_food"
    const val EDIT_FOOD = "edit_food/{foodId}"

    fun editFood(
        foodId: String
    ): String {
        return "edit_food/$foodId"
    }

}