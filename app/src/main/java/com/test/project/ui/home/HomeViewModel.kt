package com.test.project.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.project.data.remote.network.NetworkErrors
import com.test.project.domain.RequestResult
import com.test.project.domain.entit.News
import com.test.project.domain.repo.ISibgutiHerokuRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sibgutiHerokuRepo: ISibgutiHerokuRepo
) : ViewModel() {
    private val _newsState = MutableStateFlow<List<News>?>(null)
    val newsStateFlow = _newsState.asStateFlow().filterNotNull()

    private val _error = MutableStateFlow<NetworkErrors?>(null)

    fun getNews( ) {
        viewModelScope.launch {
            when (val result = sibgutiHerokuRepo.getNews()) {

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