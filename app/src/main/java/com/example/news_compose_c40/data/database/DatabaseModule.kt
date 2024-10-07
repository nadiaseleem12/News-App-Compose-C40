package com.example.news_compose_c40.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context):NewsDatabase{
        return  Room.databaseBuilder(
            context,
            NewsDatabase::class.java, " news database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase):NewsDao{
        return newsDatabase.newsDao()
    }
}