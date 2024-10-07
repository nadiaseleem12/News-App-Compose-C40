package com.example.news_compose_c40.data.repository.data_sources.local

import com.example.news_compose_c40.data.database.NewsDao
import com.example.news_compose_c40.data.model.article.Article
import com.example.news_compose_c40.data.model.source.Source
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val newsDao: NewsDao):LocalDataSource {
    override suspend fun getSources(categoryId: String): List<Source> {
       return newsDao.getSources(categoryId)
    }

    override suspend fun getArticles(sourceId: String): List<Article> {
        return newsDao.getArticles(sourceId)
    }

    override suspend fun getArticlesThatHas(searchQuery: String): List<Article> {
        return newsDao.searchArticles(searchQuery)
    }

    override suspend fun getArticle(title: String): Article {
      return newsDao.getArticle(title)
    }

    override suspend fun saveSources(sources: List<Source>) {
       newsDao.saveSources(sources)
    }

    override suspend fun saveArticles(articles: List<Article>) {
        newsDao.saveArticles(articles)
    }
}