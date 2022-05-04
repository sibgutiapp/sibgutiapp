package com.test.project.ui.login

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.test.project.R
import com.test.project.databinding.LoginFragmentBinding

class LoginFragment : Fragment(R.layout.login_fragment) {
    private val binding: LoginFragmentBinding by viewBinding()
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        auth = FirebaseAuth.getInstance()
        val email = binding.textinputedittextLogin.text.toString()
        val password = binding.textinputedittextPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        with(binding) {
                            textinputedittextPassword.text?.clear()
                            textinputlayoutPassword.error = "Incorrect login or password"
                        }
                    }
                }
        }
    }
}