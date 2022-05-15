package com.test.project.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.test.project.R
import com.test.project.databinding.LoginFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val binding: LoginFragmentBinding by viewBinding()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data).result
            account?.let {
                firebaseAuthWithGoogle(account.idToken.toString())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
    }

    private fun bindUi() {
        with(binding) {
            buttonLogin.setOnClickListener {
                login()
            }
            googleSignInButton.setOnClickListener {
                signInWithGoogle()
            }
            textinputedittextPassword.addTextChangedListener {
                textinputlayoutPassword.error = ""
            }
        }
    }

    private fun login() {
        with(binding) {
            val email = textinputedittextLogin.text.toString()
            val password = textinputedittextPassword.text.toString()
            auth = FirebaseAuth.getInstance()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        handleAuthResult(it)
                    }
            }
        }
    }

    private fun getClient(activity: Activity): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.SERVER_CLIENT_ID))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity, gso)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .addOnCompleteListener {
                handleAuthResult(it)
            }
    }

    private fun signInWithGoogle() {
        launcher.launch(
            getClient(requireActivity()).signInIntent
        )
    }

    private fun handleAuthResult(result: Task<AuthResult>) {
        with(binding) {
            if (result.isSuccessful) {
                textinputlayoutPassword.error = ""
                findNavController().navigate(
                    R.id.action_loginFragment_to_homeFragment
                )
            } else {
                textinputedittextPassword.text?.clear()
                textinputlayoutPassword.error = result.exception?.message.toString()
            }
        }
    }
}