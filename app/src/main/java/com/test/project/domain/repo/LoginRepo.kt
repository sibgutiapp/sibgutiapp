package com.test.project.domain.repo

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.test.project.ui.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow

class LoginRepo {

    val _loginState = MutableStateFlow<LoginState?>(null)
    private lateinit var auth: FirebaseAuth

    fun login(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        _loginState.value = LoginState(
                            success = true,
                            errorMessage = ""
                        )
                    } else {
                        _loginState.value = LoginState(
                            success = false,
                            errorMessage = it.exception?.message.toString()
                        )
                    }
                }
        }
    }

    fun getClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("501834887092-jucml7b8mqojet85k9vi04qgmrdvf69h.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginState.value = LoginState(
                        success = true,
                        errorMessage = ""
                    )
                } else {
                    _loginState.value = LoginState(
                        success = false,
                        errorMessage = it.exception?.message.toString()
                    )
                }
            }
    }
}