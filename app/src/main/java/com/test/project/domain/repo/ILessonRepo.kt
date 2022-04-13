package com.test.project.domain.repo

import com.test.project.domain.RequestResult
import com.test.project.domain.entity.Lesson

interface ILessonRepo {
    suspend fun getLessons() : RequestResult<List<List<Lesson>>>
}