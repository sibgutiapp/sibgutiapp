package com.test.project.domain.entity


data class News (
    val title : String,
    val id : Int,
    val description : String,
    val dateTime : String,
    val author : Author,
    val image : String
    )