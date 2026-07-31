package com.example.learnjetpack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.learnjetpack.google.GoogleSignInManager
import com.example.learnjetpack.session.SessionState
import com.example.learnjetpack.viewmodel.LoginViewModel
import com.example.learnjetpack.di.AppContainer
import com.example.learnjetpack.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val googleSignInManager = remember {
        GoogleSignInManager(context)
    }

    val viewModel: LoginViewModel = viewModel()

    LaunchedEffect(viewModel.loginSuccessful) {

        if (viewModel.loginSuccessful) {

            when (AppContainer.sessionManager.checkSession()) {

                SessionState.NeedsOnboarding -> {

                    navController.navigate(Routes.ONBOARDING) {

                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }

                    }

                }

                SessionState.LoggedIn -> {

                    navController.navigate(Routes.HOME) {

                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }

                    }

                }

                else -> Unit

            }

        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(

            enabled = !viewModel.isLoading,

            onClick = {

                viewModel.signIn(
                    googleSignInManager
                )

            }

        ) {

            Text("Continue with Google")

        }

        if (viewModel.isLoading) {

            CircularProgressIndicator()

        }

        viewModel.errorMessage?.let {

            Text(it)

        }

    }

}