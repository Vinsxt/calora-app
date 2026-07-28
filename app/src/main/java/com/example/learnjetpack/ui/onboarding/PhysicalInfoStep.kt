package com.example.learnjetpack.ui.onboarding

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.learnjetpack.ui.components.OnboardingScaffold
import com.example.learnjetpack.ui.components.OnboardingTextField
import com.example.learnjetpack.ui.components.SelectionCard
import com.example.learnjetpack.viewmodel.OnboardingViewModel

@Composable
fun PhysicalInfoStep(
    viewModel: OnboardingViewModel
) {
    OnboardingScaffold(
        title = "📋 Physical Information",
        subtitle = "Tell us a little about yourself.",
        buttonEnabled = viewModel.heightCm.isNotBlank() &&
                viewModel.weightKg.isNotBlank() &&
                viewModel.gender.isNotBlank(),
        onContinue = {
            viewModel.continueOnboarding()
        }
    ) {
        OnboardingTextField(
            value = viewModel.heightCm,
            onValueChange = {
                viewModel.updateHeight(it)
            },
            label = "Height (cm)",
            keyboardType = KeyboardType.Number
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OnboardingTextField(
            value = viewModel.weightKg,
            onValueChange = {
                viewModel.updateWeight(it)
            },
            label = "Weight (kg)",
            keyboardType = KeyboardType.Decimal
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        SelectionCard(
            text = "Male",
            selected = viewModel.gender == "Male",
            onClick = {
                viewModel.updateGender("Male")
            }
        )

        SelectionCard(
            text = "Female",
            selected = viewModel.gender == "Female",
            onClick = {
                viewModel.updateGender("Female")
            }
        )
    }
}