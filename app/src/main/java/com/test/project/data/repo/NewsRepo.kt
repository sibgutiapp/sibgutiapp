package com.test.project.data.repo

import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.remote.entity.toNews
import com.test.project.domain.RequestResult
import com.test.project.domain.entity.News
import com.test.project.domain.repo.INewsRepo

class NewsRepo(private val dataSource: SibgutiHerokuRemoteDataSource) :
    INewsRepo {
    override suspend fun getNews(): RequestResult<List<News>> {
        return when(val result = dataSource.getNews()) {

            is RequestResult.Success -> {
                RequestResult.Success(
                    result.data.map {
                        it.toNews()
                    }
                )
            }

            is RequestResult.Error -> {
                RequestResult.Error(
                    result.exception
                )
            }
        }
    }
}