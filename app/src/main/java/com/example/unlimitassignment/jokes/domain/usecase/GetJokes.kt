package com.example.unlimitassignment.jokes.domain.usecase

import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import com.example.unlimitassignment.jokes.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow

class GetJokes(private val repository: JokesRepository) {
    suspend operator fun invoke() {
        return repository.fetchJokeFromRemote()
    }

    suspend fun getFromLocal(): Flow<ArrayList<JokeDom>> {
        return repository.getJokesFromLocal()
    }
}