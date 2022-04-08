package com.test.project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.project.R
import com.test.project.data.repo.SibgutiHerokuRepo
import com.test.project.databinding.HomeFragmentBinding
import com.test.project.domain.entit.News
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
                    showNews(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel.getNews()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

   private fun showNews(news : List<News>){
        homeViewModel.getNews()
        val manager = LinearLayoutManager(context)
        adapter.addNews(news)
        viewBinding.recyclerViewHomeList.layoutManager = manager
        viewBinding.recyclerViewHomeList.adapter = adapter
    }
}