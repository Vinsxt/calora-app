package com.example.learnjetpack.session

import com.example.learnjetpack.data.repository.UserRepository

class SessionManager(

    private val repository: UserRepository

) {

    suspend fun checkSession(): SessionState {

        if (!repository.isLoggedIn()) {
            return SessionState.LoggedOut
        }

        if (!repository.isOnboardingCompleted()) {
            return SessionState.NeedsOnboarding
        }

        return SessionState.LoggedIn

    }

}