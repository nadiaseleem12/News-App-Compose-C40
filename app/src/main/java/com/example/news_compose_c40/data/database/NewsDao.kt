package com.example.news_compose_c40.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.news_compose_c40.data.model.article.Article
import com.example.news_compose_c40.data.model.source.Source

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(sources:List<Source>)

    @Upsert
    suspend fun saveArticles(articles:List<Article>)

    @Query("select * from Source where category =:categoryId")
    suspend fun getSources(categoryId:String):List<Source>

    @Query("select * from Article where sourceId =:sourceId")
    suspend fun getArticles(sourceId:String):List<Article>

    @Query("select * from Article where title like :searchQuery or description like :searchQuery or content like :searchQuery")
    suspend fun searchArticles(searchQuery: String):List<Article>

    @Query("select * from Article where title=:title limit 1")
    suspend fun getArticle(title:String):Article
}