package com.test.project.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.project.domain.entity.Lesson

@JsonClass(generateAdapter = true)
data class ApiLesson(
    @Json(name = "name")
    val name: String,
    @Json(name = "teacher")
    val teacher: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "classroom")
    val classroom: String
)

fun ApiLesson.toLesson() = Lesson(
    name = this.name,
    teacher = this.teacher,
    type = this.type,
    classroom = this.classroom
)