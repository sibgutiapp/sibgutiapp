package com.test.project.ui.login

import android.app.Activity
import android.provider.Settings.Global.getString
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.test.project.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlin.coroutines.coroutineContext

class LoginViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    private val _loginState = MutableStateFlow<LoginState?>(null)
    val loginStateFlow = _loginState.asStateFlow().filterNotNull()

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

    fun getClient(activity: Activity): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("501834887092-jucml7b8mqojet85k9vi04qgmrdvf69h.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity, gso)
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