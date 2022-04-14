package com.test.project.data.dataSource

import com.test.project.data.remote.entity.ApiLesson
import com.test.project.data.remote.entity.ApiNews
import com.test.project.data.remote.entity.ApiProfileMy
import com.test.project.data.remote.entity.ApiUser
import com.test.project.data.remote.network.INetwork
import retrofit2.http.GET


fun provideSibgutiHerokuService(network: INetwork): ISibgutiHerokuService =
    network.retrofit.create(
        ISibgutiHerokuService::class.java
    )


interface ISibgutiHerokuService {
    @GET("profile/my")
    suspend fun getProfileMy(): ApiProfileMy

    @GET("friends/my")
    suspend fun getFriends(): List<ApiUser>

    @GET("news/last")
    suspend fun getNews(): List<ApiNews>

    @GET("")
    suspend fun getSchedule(): List<List<ApiLesson>>
}