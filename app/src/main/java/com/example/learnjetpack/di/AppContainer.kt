package com.example.learnjetpack.di

import com.example.learnjetpack.data.repository.UserRepository
import com.example.learnjetpack.session.SessionManager

object AppContainer {

    val userRepository = UserRepository()

    val sessionManager = SessionManager(userRepository)

}