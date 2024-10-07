package com.example.news_compose_c40.data.connectivity

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {


    @Provides
    fun provideNetworkHandler(@ApplicationContext context: Context):NetworkHandler{
        return NetworkHandler(context)
    }
}