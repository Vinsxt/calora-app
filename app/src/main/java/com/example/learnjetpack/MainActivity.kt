package com.example.learnjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.learnjetpack.navigation.AppNavigation
import com.example.learnjetpack.ui.theme.LearnJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            LearnJetpackTheme {
                AppNavigation()
            }
        }
    }
}