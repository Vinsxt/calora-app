package com.example.learnjetpack.ui.onboarding

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learnjetpack.ui.components.OnboardingScaffold
import com.example.learnjetpack.ui.components.SelectionCard
import com.example.learnjetpack.viewmodel.OnboardingViewModel

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