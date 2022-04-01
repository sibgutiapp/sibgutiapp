package com.test.project.data.dataSource

import com.test.project.data.remote.entity.ApiUser
import com.test.project.data.remote.network.INetwork
import retrofit2.http.*

fun provideSibgutiHerokuService(network: INetwork): ISibgutiHerokuService =
    network.retrofit.create(
        ISibgutiHerokuService::class.java
    )


interface ISibgutiHerokuService {

    @GET("location/{username}")
    suspend fun getUser(
        @Path("username") username: String) : ApiUser
}