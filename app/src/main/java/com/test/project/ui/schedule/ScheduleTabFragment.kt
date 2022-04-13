package com.test.project.ui.schedule

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.project.R
import com.test.project.databinding.ScheduleTabFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleTabFragment(val position: Int) : Fragment(R.layout.schedule_tab_fragment) {

    private val binding: ScheduleTabFragmentBinding by viewBinding()
    private val model: ScheduleViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView2.text = model.tabTitles[position]
    }
}