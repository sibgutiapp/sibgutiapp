package com.test.project.ui.login

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.test.project.R
import com.test.project.databinding.LoginFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val binding: LoginFragmentBinding by viewBinding()
    private val model: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
        viewLifecycleOwner.lifecycleScope.launch {
            model.loginStateFlow.collect {
                if (it.success) {
                    binding.textinputlayoutPassword.error = it.errorMessage
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                else {
                    with(binding) {
                        textinputedittextPassword.text?.clear()
                        textinputlayoutPassword.error = it.errorMessage
                    }
                }
            }
        }
    }

    private fun bindUi() {
        with(binding) {
            buttonLogin.setOnClickListener {
                login()
            }
            textinputedittextPassword.addTextChangedListener {
                textinputlayoutPassword.error = ""
            }
        }
    }

    private fun login() {
        val email = binding.textinputedittextLogin.text.toString()
        val password = binding.textinputedittextPassword.text.toString()
        model.login(email, password)
    }
}