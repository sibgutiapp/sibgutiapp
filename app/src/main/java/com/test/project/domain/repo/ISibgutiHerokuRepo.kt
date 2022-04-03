package com.test.project.domain.repo

import com.test.project.domain.RequestResult
import com.test.project.domain.entity.ProfileMy

interface ISibgutiHerokuRepo {
    suspend fun getProfileMy(): RequestResult<ProfileMy>
}
