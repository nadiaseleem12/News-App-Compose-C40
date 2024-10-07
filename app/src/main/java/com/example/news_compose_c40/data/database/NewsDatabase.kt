package com.example.news_compose_c40.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news_compose_c40.data.model.article.Article
import com.example.news_compose_c40.data.model.source.Source

@Database(entities = [Article::class,Source::class], version = 1)
abstract class NewsDatabase :RoomDatabase(){
    abstract fun newsDao():NewsDao
}
