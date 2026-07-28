package com.example.learnjetpack.ui.onboarding

import androidx.compose.runtime.Composable
import com.example.learnjetpack.ui.components.OnboardingScaffold
import com.example.learnjetpack.ui.components.OnboardingTextField
import com.example.learnjetpack.viewmodel.OnboardingViewModel

@Composable
fun NameStep(
    viewModel: OnboardingViewModel
) {
    OnboardingScaffold(
        title = "👋 Welcome to Calora",
        subtitle = "What should we call you?",
        buttonEnabled = viewModel.displayName.isNotBlank(),
        onContinue = {
            viewModel.continueOnboarding()
        }
    ) {
        OnboardingTextField(
            value = viewModel.displayName,
            onValueChange = {
                viewModel.updateDisplayName(it)
            },
            label = "Display Name"
        )
    }
}