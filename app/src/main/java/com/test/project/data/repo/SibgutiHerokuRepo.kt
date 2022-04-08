package com.test.project.data.repo

import com.squareup.moshi.Moshi
import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.remote.entity.toUser
import com.test.project.domain.RequestResult
import com.test.project.domain.entit.News
import com.test.project.domain.entity.User
import com.test.project.domain.repo.ISibgutiHerokuRepo

class SibgutiHerokuRepo(private val dataSource: SibgutiHerokuRemoteDataSource) :
    ISibgutiHerokuRepo {

    lateinit var user: User
    lateinit var news: List<News>


    override suspend fun getUser(name: String): RequestResult<User> {
        return when (val result = dataSource.getUser(name)) {
            is RequestResult.Success -> {
                user = result.data.toUser()
                RequestResult.Success(
                    result.data.toUser()
                )
            }
            is RequestResult.Error -> {
                RequestResult.Error(
                    result.exception
                )
            }
        }
    }

    override suspend fun getNews():RequestResult<List<News>>{
        return when (val result = dataSource.getNews()){
            is RequestResult.Success -> {
                RequestResult.Success(
                    result.data
                )
            }
            is RequestResult.Error ->{
                RequestResult.Error(
                    result.exception
                )
            }
        }
    }
}