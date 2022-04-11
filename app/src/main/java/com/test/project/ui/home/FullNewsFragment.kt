package com.test.project.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.test.project.R
import com.test.project.databinding.FullNewsFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FullNewsFragment : Fragment(R.layout.full_news_fragment) {

    private val binding: FullNewsFragmentBinding by viewBinding()
    private val model: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsIndex = arguments?.getInt("position") ?: 0
        viewLifecycleOwner.lifecycleScope.launch {
            model.newsStateFlow.collect {
                binding.textView.text = it[newsIndex].title
            }
        }
    }
}