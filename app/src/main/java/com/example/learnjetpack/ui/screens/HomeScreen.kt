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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val viewModel: HomeViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.ADD_FOOD)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Food"
                )
            }
        }
    ) { padding ->

        if (viewModel.isLoading) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Loading profile...")
            }

            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome, ${viewModel.profile?.display_name ?: ""}",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            MetricCard(
                title = "🎂 Age",
                current = viewModel.metrics?.age ?: 0,
                target = viewModel.metrics?.age ?: 0,
                unit = "yrs"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "🔥 BMR",
                current = viewModel.metrics?.bmr ?: 0,
                target = viewModel.metrics?.bmr ?: 0,
                unit = "kcal"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "⚡ TDEE",
                current = viewModel.metrics?.tdee ?: 0,
                target = viewModel.metrics?.tdee ?: 0,
                unit = "kcal"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "💪 Protein",
                current = viewModel.metrics?.proteinGoal ?: 0,
                target = viewModel.metrics?.proteinGoal ?: 0,
                unit = "g"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "⚖️ Weight",
                current = viewModel.latestWeight?.weight_kg?.toInt() ?: 0,
                target = viewModel.latestWeight?.weight_kg?.toInt() ?: 0,
                unit = "kg"
            )

            Spacer(modifier = Modifier.height(32.dp))

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