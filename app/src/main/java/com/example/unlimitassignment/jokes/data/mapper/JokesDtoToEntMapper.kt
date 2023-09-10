package com.example.unlimitassignment.jokes.data.mapper

import com.example.unlimitassignment.jokes.data.entities.local.JokesEntity
import com.example.unlimitassignment.jokes.data.entities.remote.JokeDto

class JokesDtoToEntMapper {

    fun map(dto: JokeDto): JokesEntity {
        return JokesEntity(
           joke = dto.joke,
            createdAt = System.currentTimeMillis()
        )
    }



}