package com.example.learnjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnjetpack.screens.HomeScreen
import com.example.learnjetpack.screens.LoginScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val isLoggedIn = false

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "home" else "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }
    }
}