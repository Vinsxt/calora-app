package com.example.learnjetpack.ui.onboarding

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learnjetpack.ui.components.OnboardingScaffold
import com.example.learnjetpack.ui.components.SelectionCard
import com.example.learnjetpack.viewmodel.OnboardingViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LifestyleStep(
    viewModel: OnboardingViewModel
) {

    val activityOptions = listOf(
        "Sedentary",
        "Lightly Active",
        "Moderately Active",
        "Very Active",
        "Athlete"
    )

    val goalOptions = listOf(
        "Lose Fat",
        "Maintain Weight",
        "Build Muscle"
    )

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {

        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {

                OutlinedButton(
                    onClick = {

                        datePickerState.selectedDateMillis?.let {

                            val formatter =
                                SimpleDateFormat(
                                    "yyyy-MM-dd",
                                    Locale.getDefault()
                                )

                            viewModel.updateBirthDate(
                                formatter.format(Date(it))
                            )

                        }

                        showDatePicker = false

                    }
                ) {
                    Text("OK")
                }

            },
            dismissButton = {

                OutlinedButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancel")
                }

            }

        ) {

            DatePicker(
                state = datePickerState
            )

        }

    }

    OnboardingScaffold(
        title = "🎯 Lifestyle",
        subtitle = "Almost done!",
        buttonEnabled =
            viewModel.birthDate.isNotBlank() &&
                    viewModel.activityLevel.isNotBlank() &&
                    viewModel.goal.isNotBlank(),
        onContinue = {
            viewModel.continueOnboarding()
        }
    ) {

        Text("🎂 Birthday")

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedButton(
            onClick = {
                showDatePicker = true
            }
        ) {
            Text(
                if (viewModel.birthDate.isBlank())
                    "Select Birth Date"
                else
                    viewModel.birthDate
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text("🏃 Activity Level")

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        activityOptions.forEach { activity ->

            SelectionCard(
                text = activity,
                selected = viewModel.activityLevel == activity,
                onClick = {
                    viewModel.updateActivityLevel(activity)
                }
            )

        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text("🎯 Goal")

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        goalOptions.forEach { goal ->

            SelectionCard(
                text = goal,
                selected = viewModel.goal == goal,
                onClick = {
                    viewModel.updateGoal(goal)
                }
            )

        }

    }

}