package com.test.project.ui.profile

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import com.test.project.R
import android.viewbinding.library.fragment.viewBinding
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.test.project.databinding.ProfileFragmentBinding

import com.test.project.domain.entity.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding: ProfileFragmentBinding by viewBinding()
    private val model: ProfileViewModel by viewModel()

    private var loginName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.userStateFlow.collect {
                    showInfo(it)
                }
            }
        }
        bindUi()
    }

    private fun showInfo(user: User) {
        with(viewBinding) {
            with(user) {
                imageviewProfileAvatar.load(avatarUrl)
                textviewProfileDisplayName.text = displayName
                textviewProfileEmail.text = email
            }
        }
    }

    private fun bindUi() {
        with(viewBinding) {
            edittextProfileName.addTextChangedListener {
                loginName = it.toString()
            }
            buttonProfileLogin.setOnClickListener {
                model.getUser(loginName)
            }
            swipe.setOnRefreshListener {
                model.getUser(loginName)
                Handler().postDelayed(Runnable {
                    swipe.isRefreshing = false
                }, 2000)
            }
        }
    }

}