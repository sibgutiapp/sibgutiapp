package com.test.project.data.dataSource

import com.test.project.data.remote.network.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SibgutiHerokuRemoteDataSource @Inject constructor(
    private val api: ISibgutiHerokuService
) {
    suspend fun getProfileMy() = safeApiCall {
        api.getProfileMy()
    }

    suspend fun getFriends() = safeApiCall {
        api.getFriends()
    }

    suspend fun getNews() = safeApiCall {
        api.getNews()
    }

    suspend fun getSchedule() = safeApiCall{
        api.getSchedule()
    }
}