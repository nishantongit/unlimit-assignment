package com.example.unlimitassignment.jokes.data.api

import com.example.unlimitassignment.jokes.data.entities.remote.JokeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesService {
    @GET("api")
    suspend fun getJokes(@Query("format")  format:String): Response<JokeDto>
}