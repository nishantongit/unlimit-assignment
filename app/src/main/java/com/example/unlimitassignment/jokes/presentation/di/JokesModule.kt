package com.example.unlimitassignment.jokes.presentation.di

import android.content.Context
import com.example.unlimitassignment.base.db.AppDatabase
import com.example.unlimitassignment.jokes.data.api.ApiClient
import com.example.unlimitassignment.jokes.data.api.JokesService
import com.example.unlimitassignment.jokes.data.datasource.local.JokesDao
import com.example.unlimitassignment.jokes.data.datasource.local.JokesLDS
import com.example.unlimitassignment.jokes.data.datasource.local.JokesLDSImpl
import com.example.unlimitassignment.jokes.data.datasource.remote.JokesRDS
import com.example.unlimitassignment.jokes.data.datasource.remote.JokesRDSImpl
import com.example.unlimitassignment.jokes.data.mapper.JokesDtoToEntMapper
import com.example.unlimitassignment.jokes.data.mapper.JokesEntToDomMapper
import com.example.unlimitassignment.jokes.data.repository.JokesRepositoryImpl
import com.example.unlimitassignment.jokes.domain.repository.JokesRepository
import com.example.unlimitassignment.jokes.domain.usecase.GetJokes
import com.example.unlimitassignment.jokes.presentation.ui.viewmodel.factory.JokesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class JokesModule(var context: Context) {


    @Provides
    fun provideJokesRepository(
        jokesLDS: JokesLDS,
        jokesRDS: JokesRDS
    ): JokesRepository {
        return JokesRepositoryImpl(jokesRDS, jokesLDS, JokesDtoToEntMapper(), JokesEntToDomMapper())
    }

    @Provides
    fun provideJokesRDS(jokesService: JokesService): JokesRDS {
        return JokesRDSImpl(jokesService)
    }

    @Provides
    fun provideJokesService(): JokesService {
        return ApiClient.getService()
    }

    @Provides
    fun provideJokesLDS(jokesDao: JokesDao): JokesLDS {
        return JokesLDSImpl(jokesDao)
    }

    @Provides
    fun provideJokesDao(): JokesDao {
        return AppDatabase.getDatabase(context).jokesDao()
    }

    @Provides
    fun provideJokesViewModelFactory(jokesUseCase: GetJokes): JokesViewModelFactory {
        return JokesViewModelFactory(jokesUseCase)
    }

    @Provides
    fun provideJokesUseCase(jokesRepository: JokesRepository): GetJokes {
        return GetJokes(jokesRepository)
    }


}