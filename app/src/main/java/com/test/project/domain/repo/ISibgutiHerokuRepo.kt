package com.test.project.domain.repo

import com.test.project.domain.RequestResult
import com.test.project.domain.entity.News
import com.test.project.domain.entity.User

interface ISibgutiHerokuRepo {
    suspend fun getUser(name: String): RequestResult<User>
    suspend fun getNews(): RequestResult<List<News>>
}
