package com.example.news_compose_c40.data.repository

import androidx.room.Insert
import com.example.news_compose_c40.data.repository.data_sources.local.LocalDataSource
import com.example.news_compose_c40.data.repository.data_sources.local.LocalDataSourceImpl
import com.example.news_compose_c40.data.repository.data_sources.remote.RemoteDataSource
import com.example.news_compose_c40.data.repository.data_sources.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface repositoryModule {
    @Binds
    fun bindNewsRepo(newsRepoImpl: NewsRepoImpl):NewsRepo

    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):RemoteDataSource

    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl):LocalDataSource
}