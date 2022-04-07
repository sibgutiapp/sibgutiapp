package com.test.project.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.project.R
import com.test.project.databinding.HomeFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewBinding: HomeFragmentBinding by viewBinding()
    private val adapterNews: HomeNewsListAdapter = HomeNewsListAdapter()
    private val model: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.getNews()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    model.newsStateFlow.collect {
                        with(adapterNews) {
                            setUpdatedData(it)
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        bindUi(view.context)
    }

    private fun bindUi(context: Context?) {
        with(viewBinding) {
            with(recyclerViewHomeList) {
                adapter = adapterNews
                val manager = LinearLayoutManager(context)
                layoutManager = manager
            }
            swipeRefreshHome.setOnRefreshListener {
                model.getNews()
                swipeRefreshHome.isRefreshing = false
            }
        }
    }
}