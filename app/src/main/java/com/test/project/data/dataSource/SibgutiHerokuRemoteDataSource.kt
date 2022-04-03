package com.test.project.data.dataSource

import com.test.project.data.remote.network.safeApiCall

class SibgutiHerokuRemoteDataSource(
    private val api: ISibgutiHerokuService
) {
    suspend fun getProfileMy() = safeApiCall {
        api.getProfileMy()
    }
}