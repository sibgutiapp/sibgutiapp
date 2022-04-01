package com.test.project.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.project.domain.entity.User

@JsonClass(generateAdapter = true)
data class ApiUser(
    @Json(name = "displayName")
    val displayName: String?,
    @Json(name = "userId")
    val userId: Int?,
    @Json(name = "avatarUrl")
    val avatarUrl: String?,
    @Json(name = "email")
    val email: String?
)

fun ApiUser?.toUser() = User(
    displayName = this?.displayName ?: "",
    id = this?.userId ?: -1,
    avatarUrl = this?.avatarUrl ?: "",
    email = this?.email ?: ""
)
