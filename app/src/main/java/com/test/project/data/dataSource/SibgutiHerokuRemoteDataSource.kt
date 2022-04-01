package com.test.project.data.dataSource

import com.test.project.data.remote.network.safeApiCall

class SibgutiHerokuRemoteDataSource(
    private val api: ISibgutiHerokuService
) {
    suspend fun getUser(login: String) = safeApiCall {
        api.getUser(login)
    }
}