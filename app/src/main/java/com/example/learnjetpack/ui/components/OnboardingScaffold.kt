package com.example.learnjetpack.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScaffold(
    title: String,
    subtitle: String,
    buttonText: String = "Continue",
    buttonEnabled: Boolean = true,
    onContinue: () -> Unit,
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Top
    ) {

        Spacer(
            modifier = Modifier.height(80.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        content()

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = onContinue,
            enabled = buttonEnabled
        ) {
            Text(buttonText)
        }

    }

}