package com.test.project.ui.login

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.project.R
import com.test.project.databinding.LoginFragmentBinding

class LoginFragment: Fragment(R.layout.login_fragment) {
    private val binding: LoginFragmentBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
}