package com.test.project.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.project.R
import com.test.project.databinding.HomeFragmentBinding


class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewBinding: HomeFragmentBinding by viewBinding()
    private val adapter: HomeListAdapter = HomeListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.HomeList.adapter = adapter
        val manager = LinearLayoutManager(view.context)
        viewBinding.HomeList.layoutManager = manager
    }
}