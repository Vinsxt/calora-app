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
import androidx.compose.runtime.setValue
import com.example.learnjetpack.ui.screens.AddFoodScreen

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
                Routes.LOGIN

            SessionState.NeedsOnboarding ->
                Routes.ONBOARDING

            SessionState.LoggedIn ->
                Routes.HOME

            SessionState.Loading ->
                Routes.LOGIN // unreachable because of the early return
        }
    ) {

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(Routes.ONBOARDING) {
            OnboardingScreen(navController)
        }

        composable(Routes.ADD_FOOD) {
            AddFoodScreen(navController)
        }

        composable(Routes.EDIT_FOOD) { backStackEntry ->
            val foodId =
                backStackEntry.arguments
                    ?.getString("foodId")
            AddFoodScreen(
                navController = navController,
                foodId = foodId
            )
        }
    }
}