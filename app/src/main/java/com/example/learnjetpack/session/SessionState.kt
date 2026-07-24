package com.example.learnjetpack.session

sealed class SessionState {

    data object Loading : SessionState()

    data object LoggedOut : SessionState()

    data object NeedsOnboarding : SessionState()

    data object LoggedIn : SessionState()

}