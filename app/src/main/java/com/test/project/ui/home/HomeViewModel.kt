package com.test.project.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.project.data.remote.network.NetworkErrors
import com.test.project.domain.RequestResult
import com.test.project.domain.entity.News
import com.test.project.domain.repo.INewsRepo
import com.test.project.domain.repo.IProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepo: INewsRepo
    ) : ViewModel() {

    private val _newsState = MutableStateFlow<List<News>?>(null)
    val newsStateFlow = _newsState.asStateFlow().filterNotNull()

    private val _error = MutableStateFlow<NetworkErrors?>(null)

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            when (val result = newsRepo.getNews()) {
                is RequestResult.Success -> {
                    _newsState.emit(result.data)
                }
                is RequestResult.Error -> {
                    _error.emit(result.exception)
                }
            }
        }
    }
}