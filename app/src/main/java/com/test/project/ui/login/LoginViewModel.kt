package com.test.project.ui.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

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
                    }
                    else {
                        _loginState.value = LoginState(
                            success = false,
                            errorMessage = it.exception?.message.toString()
                        )
                    }
                }
        }
    }
}