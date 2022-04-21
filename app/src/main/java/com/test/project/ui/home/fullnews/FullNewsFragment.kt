package com.test.project.ui.home.fullnews

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.test.project.R
import com.test.project.databinding.FullNewsFragmentBinding
import com.test.project.domain.entity.News
import com.test.project.ui.home.HomeNewsListAdapter
import com.test.project.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FullNewsFragment : Fragment(R.layout.full_news_fragment) {

    private val binding: FullNewsFragmentBinding by viewBinding()
    private val model: HomeViewModel by viewModels()
    private val adapterFullNewsList: FullNewsListAdapter = FullNewsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsIndex = arguments?.getInt("position") ?: 0
        viewLifecycleOwner.lifecycleScope.launch {
            model.newsStateFlow.collect {
                bindUi(it[newsIndex])
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                launch {
                    model.newsStateFlow.collect {
                        adapterFullNewsList.setUpdatedData(it)
                    }
                }
            }
        }
    }

    private fun bindUi(news: News) {
        with(binding) {
            with(news) {
                textviewItemTitle.text = title
                textviewItemTitle.typeface = android.graphics.Typeface.DEFAULT_BOLD
                textviewItemAuthor.text = author?.fullName ?: ""
                textviewItemDate.text = dateTime
                textviewItemDescription.text = description
                textviewItemDescription.movementMethod = ScrollingMovementMethod()
                imageviewItemImage.load(imageUrl)
                imageviewAuthorAvatar.load(author?.avatarUrl) {
                    transformations(CircleCropTransformation())
                }
            }
            with(recyclerviewFullNews) {
                adapter = adapterFullNewsList
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false)
                adapterFullNewsList.setOnItemClickListener(object :
                    FullNewsListAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val bundle = Bundle()
                        bundle.putInt("position", position)
                        findNavController().navigate(
                            R.id.action_fullNewsFragment_self,
                            bundle
                        )
                    }
                })
            }
        }
    }
}