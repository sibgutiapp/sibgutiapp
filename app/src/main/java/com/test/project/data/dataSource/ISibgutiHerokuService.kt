package com.test.project.data.dataSource

import com.test.project.data.remote.entity.ApiFriend
import com.test.project.data.remote.entity.ApiFriends
import com.test.project.data.remote.entity.ApiProfileMy
import com.test.project.data.remote.network.INetwork
import com.test.project.domain.entity.Friend
import retrofit2.Call
import retrofit2.http.*


fun provideSibgutiHerokuService(network: INetwork): ISibgutiHerokuService =
    network.retrofit.create(
        ISibgutiHerokuService::class.java
    )


interface ISibgutiHerokuService {
    @GET("profile/my")
    suspend fun getProfileMy(): ApiProfileMy

    @GET("friends/my")
    suspend fun getFriends(): ApiFriends
}