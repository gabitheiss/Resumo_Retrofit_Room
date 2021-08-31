package com.example.resumo_retrofit_room.di

import android.content.Context
import com.example.resumo_retrofit_room.dao.GithubDao
import com.example.resumo_retrofit_room.database.AppDatabase
import com.example.resumo_retrofit_room.repository.GithubRepository
import com.example.resumo_retrofit_room.service.GithubServices
import com.example.resumo_retrofit_room.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {


    @Provides
    fun provideGithubRepository(dao: GithubDao, services: GithubServices): GithubRepository = GithubRepository(dao, services)


    @Provides
    fun provideGithuServices(): GithubServices = RetrofitService.getGithubServices()


    @Provides
    fun provideGithubDao(@ApplicationContext context: Context) : GithubDao{
        return AppDatabase.getDatabase(context).getGithubDao()
    }
}