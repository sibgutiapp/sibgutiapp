package com.test.project.data.repo

import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.remote.entity.toProfileMy
import com.test.project.domain.RequestResult
import com.test.project.domain.entity.ProfileMy
import com.test.project.domain.repo.ISibgutiHerokuRepo

class SibgutiHerokuRepo(private val dataSource: SibgutiHerokuRemoteDataSource) :
    ISibgutiHerokuRepo {

    lateinit var profileMy: ProfileMy

    override suspend fun getProfileMy(): RequestResult<ProfileMy> {
        return when (val result = dataSource.getProfileMy()) {
            is RequestResult.Success -> {
                profileMy = result.data.toProfileMy()
                with(profileMy) {
                    println(avatarUrl)
                    println(fullName)
                    println(phoneNumber)
                }
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