package com.example.unlimitassignment.jokes.data.datasource.remote

import com.example.unlimitassignment.jokes.data.entities.remote.JokeDto
import retrofit2.Response


interface JokesRDS {
    suspend fun fetchJokes(): Response<JokeDto>
}