package com.test.project.data.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.project.domain.entit.News

@JsonClass(generateAdapter = true)
data class ApiNews(
        @Json(name = "id")
        val newsId : Int? ,
        @Json(name = "title")
        val title : String?,
        @Json(name = "description")
        val description : String?,
        @Json(name = "date_time")
        val dateTime : String?,
        @Json(name = "author")
        val author : ApiAuthor?,
        @Json(name = "image_url")
        val image : String?
)
fun ApiNews?.toNews() = News(
        title = this?.title ?: "",
        id = this?.newsId ?: -1,
        description = this?.description ?: "",
        dateTime = this?.dateTime ?: "",
        author = this?.author?:ApiAuthor(0,"","","",""),
        image = this?.image?:""
)

data class ApiListNews(
        val listNews: List<ApiNews>
)
fun ApiListNews?.toListNews(): MutableList<News> {
        val tempList = mutableListOf<News>()
        this?.listNews?.forEach{
                tempList.add(it.toNews())
        }
        return tempList
}