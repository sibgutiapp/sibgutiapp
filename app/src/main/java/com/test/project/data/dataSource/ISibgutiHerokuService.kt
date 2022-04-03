package com.test.project.data.dataSource

import com.test.project.data.remote.entity.ApiProfileMy
import com.test.project.data.remote.network.INetwork
import retrofit2.http.*

fun provideSibgutiHerokuService(network: INetwork): ISibgutiHerokuService =
    network.retrofit.create(
        ISibgutiHerokuService::class.java
    )


interface ISibgutiHerokuService {
    @GET("profile/my")
    suspend fun getProfileMy(): ApiProfileMy
}