package com.test.project.ui.home.fullnews

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.test.project.R
import com.test.project.databinding.FullNewsFragmentBinding
import com.test.project.domain.entity.News
import com.test.project.ui.home.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FullNewsFragment : Fragment(R.layout.full_news_fragment) {

    private val binding: FullNewsFragmentBinding by viewBinding()
    private val model: HomeViewModel by viewModel()
    private val adapterFullNewsList: FullNewsListAdapter = FullNewsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsIndex = arguments?.getInt("position") ?: 0
        viewLifecycleOwner.lifecycleScope.launch {
            model.newsStateFlow.collect {
                bindUi(it[newsIndex])
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
                    false
                )
            }
        }
    }
}