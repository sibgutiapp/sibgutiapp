package com.test.project.data.repo

import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.remote.entity.ApiFriend
import com.test.project.data.remote.entity.toFriend
import com.test.project.data.remote.entity.toProfileMy
import com.test.project.domain.RequestResult
import com.test.project.domain.entity.Friend
import com.test.project.domain.entity.ProfileMy
import com.test.project.domain.repo.ISibgutiHerokuRepo

class SibgutiHerokuRepo(private val dataSource: SibgutiHerokuRemoteDataSource) :
    ISibgutiHerokuRepo {

    override suspend fun getFriends(): RequestResult<List<Friend>> {
        return when (val result = dataSource.getFriends()) {

            is RequestResult.Success -> {
                RequestResult.Success(
                    result.data.map {
                        it.toFriend()
                    }
                )
            }
            is RequestResult.Error -> {
                println(result.exception)
                RequestResult.Error(
                    result.exception
                )
            }
        }
    }

    override suspend fun getProfileMy(): RequestResult<ProfileMy> {
        return when (val result = dataSource.getProfileMy()) {
            is RequestResult.Success -> {
                RequestResult.Success(
                    result.data.toProfileMy()
                )
            }
            is RequestResult.Error -> {
                RequestResult.Error(
                    result.exception
                )
            }
        }
    }
}