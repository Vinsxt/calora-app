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
import com.example.learnjetpack.ui.components.MetricCard
import com.example.learnjetpack.viewmodel.HomeViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

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
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
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

            Text(
                text = "Welcome, ${viewModel.profile?.display_name ?: ""}",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            MetricCard(
                title = "🎂 Age",
                value = "${viewModel.metrics?.age}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "🔥 BMR",
                value = "${viewModel.metrics?.bmr} kcal"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "⚡ TDEE",
                value = "${viewModel.metrics?.tdee} kcal"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "💪 Protein Target",
                value = "${viewModel.metrics?.proteinGoal} g"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "⚖️ Current Weight",
                value = "${viewModel.latestWeight?.weight_kg} kg"
            )

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