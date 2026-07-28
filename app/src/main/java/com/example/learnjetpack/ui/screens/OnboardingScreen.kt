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
                    OutlinedTextField(
                        value = viewModel.displayName,
                        onValueChange = {
                            viewModel.updateDisplayName(it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Display Name")
                        }
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
                    OutlinedTextField(
                        value = viewModel.heightCm,
                        onValueChange = {
                            viewModel.updateHeight(it)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Height (cm)")
                        }
                    )
                }
            }

            2 -> {
                // Weight page
            }

            3 -> {
                // Gender page
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