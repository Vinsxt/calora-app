package com.example.learnjetpack.auth

import com.example.learnjetpack.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken

class AuthRepository {

    suspend fun signInWithGoogle(idToken: String) {

        supabase.auth.signInWith(IDToken){
            this.idToken = idToken
            provider = Google
        }

    }

}