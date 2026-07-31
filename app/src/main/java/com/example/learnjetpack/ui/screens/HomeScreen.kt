package com.example.learnjetpack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.learnjetpack.navigation.Routes

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Home Screen")

        Button(
            onClick = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.HOME) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text("Logout")
        }
    }
}