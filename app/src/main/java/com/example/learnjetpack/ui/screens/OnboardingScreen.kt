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
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnjetpack.ui.components.OnboardingTextField
import com.example.learnjetpack.ui.components.SelectionCard
import com.example.learnjetpack.ui.onboarding.NameStep
import com.example.learnjetpack.ui.onboarding.PhysicalInfoStep
import com.example.learnjetpack.ui.onboarding.LifestyleStep


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
                NameStep(viewModel)
            }

            1 -> {
                PhysicalInfoStep(viewModel)
            }

            2 -> {
                LifestyleStep(viewModel)
            }
        }
    }
}