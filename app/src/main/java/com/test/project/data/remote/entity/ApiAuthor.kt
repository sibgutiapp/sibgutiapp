package com.test.project.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiAuthor(
    @Json(name = "id")
    val id : Int,
    @Json(name = "full_name")
    val fullName : String,
    @Json(name = "group")
    val group : String,
    @Json(name = "tel")
    val phoneNumber : String,
    @Json(name = "avatar_url")
    val avatar : String
)