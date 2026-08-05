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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.learnjetpack.model.FoodLog
import com.example.learnjetpack.ui.components.FoodLogCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val viewModel: HomeViewModel = viewModel()

    var foodToDelete by remember {
        mutableStateOf<FoodLog?>(null)
    }

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
                title = "🔥 Calories",
                current = viewModel.dailyNutrition?.calories?.toInt() ?: 0,
                target = viewModel.metrics?.tdee ?: 0,
                unit = "kcal"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MetricCard(
                title = "💪 Protein",
                current = viewModel.dailyNutrition?.protein?.toInt() ?: 0,
                target = viewModel.metrics?.proteinGoal ?: 0,
                unit = "g"
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
                title = "⚖️ Weight",
                current = viewModel.latestWeight?.weight_kg?.toInt() ?: 0,
                target = viewModel.latestWeight?.weight_kg?.toInt() ?: 0,
                unit = "kg"
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = "Today's Food",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            val groupedFoods =
                viewModel.foodLogs.groupBy {
                    it.meal_type
                }

            listOf(
                "Breakfast",
                "Lunch",
                "Dinner",
                "Snack"
            ).forEach { meal ->

                val foods =
                    groupedFoods[meal]
                        ?: emptyList()

                if (foods.isNotEmpty()) {
                    Text(
                        text = meal,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    foods.forEach { food ->
                        FoodLogCard(
                            food = food,
                            onDelete = {
                                foodToDelete = food
                            }
                        )
                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
            }

            if (viewModel.foodLogs.isEmpty()) {

                Text(
                    text = "No meals logged today 🍽️",
                    style = MaterialTheme.typography.bodyLarge
                )

            }

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

    foodToDelete?.let { food ->

        AlertDialog(
            onDismissRequest = {
                foodToDelete = null
            },
            title = {
                Text("Delete Food")
            },
            text = {
                Text("Delete ${food.food_name}?")
            },
            confirmButton = {

                TextButton(
                    onClick = {

                        food.id?.let {
                            viewModel.deleteFood(it)
                        }

                        foodToDelete = null

                    }
                ) {
                    Text("Delete")
                }

            },
            dismissButton = {

                TextButton(
                    onClick = {
                        foodToDelete = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}