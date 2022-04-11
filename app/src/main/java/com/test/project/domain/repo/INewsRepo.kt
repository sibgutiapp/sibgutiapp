package com.test.project.domain.repo

import com.test.project.domain.RequestResult
import com.test.project.domain.entity.News

interface INewsRepo {
    suspend fun getNews(): RequestResult<List<News>>
}