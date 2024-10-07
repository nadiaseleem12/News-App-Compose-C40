package com.example.news_compose_c40.data.model.article

import com.example.news_compose_c40.data.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<Article>? = null,

    ): BaseResponse()
