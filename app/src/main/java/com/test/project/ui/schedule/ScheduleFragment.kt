package com.test.project.ui.schedule

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.test.project.R
import com.test.project.databinding.ScheduleFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : Fragment(R.layout.schedule_fragment) {

    private val model: ScheduleViewModel by viewModel()
    private val binding: ScheduleFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}