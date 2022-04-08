package com.test.project.data.dataSource

import com.test.project.data.remote.entity.ApiListNews
import com.test.project.data.remote.entity.ApiUser
import com.test.project.data.remote.network.INetwork
import com.test.project.domain.entit.News
import retrofit2.http.*

fun provideSibgutiHerokuService(network: INetwork): ISibgutiHerokuService =
    network.retrofit.create(
        ISibgutiHerokuService::class.java
    )


interface ISibgutiHerokuService {

    @GET("location/{username}")
    suspend fun getUser(
        @Path("username") username: String) : ApiUser

    @GET("news/last")
    suspend fun getNews() : List<News>
}