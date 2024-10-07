package com.example.news_compose_c40.data.model.article

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.news_compose_c40.data.model.source.Source
import com.google.gson.annotations.SerializedName
@Entity(foreignKeys = [ForeignKey(Source::class,["id"],["sourceId"], onDelete = ForeignKey.CASCADE)])
data class Article(

    @field:SerializedName("publishedAt")
	val publishedAt: String? = null,

    @field:SerializedName("author")
	val author: String? = null,

    @field:SerializedName("urlToImage")
	val urlToImage: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    var sourceId :String?=null,

    @field:SerializedName("title")
	val title: String?=null,

    @field:SerializedName("url")
	val url: String? = null,

    @field:SerializedName("content")
	val content: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
){
    @Ignore
    @field:SerializedName("source")
    val source: Source? = null
}
