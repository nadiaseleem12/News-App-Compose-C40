package com.example.news_compose_c40.data.repository.data_sources.local

import com.example.news_compose_c40.data.model.article.Article
import com.example.news_compose_c40.data.model.source.Source

interface LocalDataSource {
    suspend fun getSources(categoryId:String):List<Source>

    suspend fun getArticles(sourceId:String) :List<Article>

    suspend fun getArticlesThatHas(searchQuery:String):List<Article>

    suspend fun getArticle(title:String): Article

    suspend fun saveSources(sources: List<Source>)

    suspend fun saveArticles(articles:List<Article>)

}
