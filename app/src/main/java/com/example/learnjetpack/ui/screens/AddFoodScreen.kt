package com.example.learnjetpack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.learnjetpack.viewmodel.AddFoodViewModel

@Composable
fun AddFoodScreen(
    navController: NavHostController,
    viewModel: AddFoodViewModel = viewModel()
) {

    LaunchedEffect(viewModel.saveSuccess) {
        if (viewModel.saveSuccess) {
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {

        Text(
            text = "Add Food",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = viewModel.foodName,
            onValueChange = {
                viewModel.updateFoodName(it)
            },
            label = {
                Text("Food Name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.calories,
            onValueChange = {
                viewModel.updateCalories(it)
            },
            label = {
                Text("Calories")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.protein,
            onValueChange = {
                viewModel.updateProtein(it)
            },
            label = {
                Text("Protein (g)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.carbs,
            onValueChange = {
                viewModel.updateCarbs(it)
            },
            label = {
                Text("Carbs (g)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.fat,
            onValueChange = {
                viewModel.updateFat(it)
            },
            label = {
                Text("Fat (g)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.fiber,
            onValueChange = {
                viewModel.updateFiber(it)
            },
            label = {
                Text("Fiber (g)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.quantity,
            onValueChange = {
                viewModel.updateQuantity(it)
            },
            label = {
                Text("Quantity (g)")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Meal Type")

        listOf(
            "Breakfast",
            "Lunch",
            "Dinner",
            "Snack"
        ).forEach { type ->

            Row {

                RadioButton(
                    selected = viewModel.mealType == type,
                    onClick = {
                        viewModel.updateMealType(type)
                    }
                )

                Text(
                    text = type,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.saveFood()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Food")
        }

    }

}