package com.example.learnjetpack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.learnjetpack.navigation.Routes
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnjetpack.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val viewModel: HomeViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (viewModel.isLoading) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Loading profile...")
            }

            return
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text("Welcome")

            Text(viewModel.profile?.display_name ?: "No Name")

            Text(viewModel.profile?.height_cm?.toString() ?: "-")

            Text(viewModel.profile?.sex ?: "-")

            Text(viewModel.profile?.goal ?: "-")

            Text("Welcome, ${viewModel.profile?.display_name}")

            Spacer(modifier = Modifier.height(24.dp))

            Text("Age: ${viewModel.metrics?.age}")

            Text("BMR: ${viewModel.metrics?.bmr} kcal")

            Text("TDEE: ${viewModel.metrics?.tdee} kcal")

            Text("Protein Target: ${viewModel.metrics?.proteinGoal} g")

            Spacer(modifier = Modifier.height(24.dp))

            Text("Current Weight: ${viewModel.latestWeight?.weight_kg} kg")

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
}