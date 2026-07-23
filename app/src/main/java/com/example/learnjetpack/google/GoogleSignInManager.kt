package com.example.learnjetpack.google

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.learnjetpack.config.GoogleAuthConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException

class GoogleSignInManager(
    private val context: Context
) {

    private val credentialManager = CredentialManager.create(context)

    suspend fun getGoogleIdToken(): String {

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(GoogleAuthConfig.WEB_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(
            context = context,
            request = request
        )

        val credential = result.credential

        if (
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {

            try {

                val googleCredential =
                    GoogleIdTokenCredential.createFrom(credential.data)

                return googleCredential.idToken

            } catch (e: GoogleIdTokenParsingException) {

                throw Exception("Failed to parse Google ID Token.")

            }

        }

        throw Exception("Google Sign-In failed.")

    }
}