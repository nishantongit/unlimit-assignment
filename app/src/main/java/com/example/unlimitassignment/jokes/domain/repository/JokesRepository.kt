package com.example.unlimitassignment.jokes.domain.repository

import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import kotlinx.coroutines.flow.Flow


interface JokesRepository {
    suspend fun fetchJokeFromRemote()
    suspend fun getJokesFromLocal(): Flow<ArrayList<JokeDom>>
}