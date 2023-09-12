package com.example.unlimitassignment.jokes.data.datasource.local

import com.example.unlimitassignment.jokes.data.entities.local.JokesEntity
import kotlinx.coroutines.flow.Flow

interface JokesLDS {
    suspend fun getJokes(): Flow<List<JokesEntity>>
    suspend fun insert(joke: JokesEntity)
    suspend fun deleteExtraRecords()
}