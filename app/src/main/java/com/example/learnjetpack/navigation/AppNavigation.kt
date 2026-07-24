package com.example.learnjetpack.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnjetpack.ui.screens.HomeScreen
import com.example.learnjetpack.ui.screens.LoginScreen
import com.example.learnjetpack.di.AppContainer
import com.example.learnjetpack.screens.OnboardingScreen
import com.example.learnjetpack.session.SessionState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    var sessionState by remember {
        mutableStateOf<SessionState>(SessionState.Loading)
    }

    LaunchedEffect(Unit) {

        sessionState =
            AppContainer.sessionManager.checkSession()

    }

    if (sessionState == SessionState.Loading) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()

        }

        return

    }

    NavHost(
        navController = navController,
        startDestination = when (sessionState) {

            SessionState.LoggedOut ->
                "login"

            SessionState.NeedsOnboarding ->
                "onboarding"

            SessionState.LoggedIn ->
                "home"

            SessionState.Loading ->
                "login" // unreachable because of the early return
        }
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("onboarding") {
            OnboardingScreen()
        }
    }
}