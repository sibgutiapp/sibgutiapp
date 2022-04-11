package com.test.project.ui.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.project.R
import com.test.project.databinding.HomeFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewBinding: HomeFragmentBinding by viewBinding()
    private val adapter: HomeListAdapter = HomeListAdapter()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.newsStateFlow.collect {
                    adapter.updateNews(it)
                }
            }
        }
        bindUi()
    }

    private fun bindUi(){
        val manager = LinearLayoutManager(context)
        viewBinding.recyclerViewHomeList.layoutManager = manager
        viewBinding.recyclerViewHomeList.adapter = adapter
        viewBinding.swipeHomeFragment.setOnRefreshListener {
            homeViewModel.getNews()
            viewBinding.swipeHomeFragment.isRefreshing = false
        }
    }
}