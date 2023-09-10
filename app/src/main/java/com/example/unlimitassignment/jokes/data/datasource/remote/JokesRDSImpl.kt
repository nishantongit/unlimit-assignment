package com.example.unlimitassignment.jokes.data.datasource.remote

import com.example.unlimitassignment.jokes.data.api.JokesService
import com.example.unlimitassignment.jokes.data.entities.remote.JokeDto
import retrofit2.Response

class JokesRDSImpl(private val service: JokesService) : JokesRDS {
    override suspend fun fetchJokes(): Response<JokeDto> {
        return service.getJokes("json")
    }
}