package com.test.project.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.test.project.R
import com.test.project.databinding.ProfileFragmentBinding
import android.viewbinding.library.fragment.viewBinding

class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private val viewBinding: ProfileFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}