package com.test.project.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.test.project.R
import com.test.project.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewBinding: HomeFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}