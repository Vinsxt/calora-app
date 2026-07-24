package com.example.learnjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnjetpack.ui.screens.HomeScreen
import com.example.learnjetpack.ui.screens.LoginScreen
import com.example.learnjetpack.data.remote.supabase
import io.github.jan.supabase.auth.auth

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val isLoggedIn =
        supabase.auth.currentSessionOrNull() != null

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