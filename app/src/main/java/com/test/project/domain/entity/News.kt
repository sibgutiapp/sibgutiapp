package com.test.project.domain.entit

import com.test.project.data.remote.entity.ApiAuthor

data class News (
    val title : String,
    val id : Int,
    val description : String,
    val dateTime : String,
    val author : ApiAuthor,
    val image : String
    )