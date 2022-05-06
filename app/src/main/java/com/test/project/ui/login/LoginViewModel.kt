package com.test.project.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.test.project.domain.repo.LoginRepo
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull

class LoginViewModel(
    private val loginRepo: LoginRepo
) : ViewModel() {

    val loginStateFlow = loginRepo._loginState.asStateFlow().filterNotNull()

    fun login(email: String, password: String) {
        loginRepo.login(email, password)
    }

    fun authWithGoogle(idToken: String) {
        loginRepo.firebaseAuthWithGoogle(idToken)
    }

    fun signInWithGoogle(context: Activity) : Intent {
        return loginRepo.getClient(context).signInIntent
    }
}