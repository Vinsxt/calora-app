package com.example.learnjetpack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnjetpack.viewmodel.OnboardingViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.learnjetpack.ui.components.OnboardingScaffold
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.learnjetpack.ui.components.OnboardingTextField
import com.example.learnjetpack.ui.components.SelectionCard

@Composable
fun OnboardingScreen() {

    val viewModel: OnboardingViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(
            modifier = Modifier.height(80.dp)
        )
        when (viewModel.currentStep) {

            0 -> {
                OnboardingScaffold(
                    title = "👋 Welcome to Calora",
                    subtitle = "What should we call you?",
                    buttonEnabled = viewModel.displayName.isNotBlank(),
                    onContinue = {
                        viewModel.nextStep()
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

            1 -> {
                OnboardingScaffold(
                    title = "📏 Height",
                    subtitle = "How tall are you?",
                    buttonEnabled = viewModel.heightCm.isNotBlank(),
                    onContinue = {
                        viewModel.nextStep()
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
                }
            }

            2 -> {
                OnboardingScaffold(
                    title = "⚖️ Weight",
                    subtitle = "What's your current weight?",
                    buttonEnabled = viewModel.weightKg.isNotBlank(),
                    onContinue = {
                        viewModel.nextStep()
                    }
                ) {
                    OnboardingTextField(
                        value = viewModel.weightKg,
                        onValueChange = {
                            viewModel.updateWeight(it)
                        },
                        label = "Weight (kg)",
                        keyboardType = KeyboardType.Decimal
                    )
                }
            }

            3 -> {
                OnboardingScaffold(
                    title = "🚻 Gender",
                    subtitle = "Select your gender",
                    buttonEnabled = viewModel.gender.isNotBlank(),
                    onContinue = {
                        viewModel.nextStep()
                    }
                ) {
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

            4 -> {
                // Birthday page
            }

            5 -> {
                // Activity page
            }

            6 -> {
                // Goal page
            }
        }
    }
}