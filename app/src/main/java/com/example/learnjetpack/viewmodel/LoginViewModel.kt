package com.example.learnjetpack.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnjetpack.auth.AuthRepository
import com.example.learnjetpack.google.GoogleSignInManager
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var loginSuccessful by mutableStateOf(false)
        private set

    fun signIn(
        googleSignInManager: GoogleSignInManager
    ) {

        viewModelScope.launch {

            isLoading = true
            errorMessage = null

            try {

                val idToken =
                    googleSignInManager.getGoogleIdToken()

                repository.signInWithGoogle(idToken)

                loginSuccessful = true

            } catch (e: Exception) {

                errorMessage = e.message

            } finally {

                isLoading = false

            }

        }

    }

}