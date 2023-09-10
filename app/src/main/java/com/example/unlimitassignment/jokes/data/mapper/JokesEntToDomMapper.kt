package com.example.unlimitassignment.jokes.data.mapper

import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import com.example.unlimitassignment.jokes.data.entities.local.JokesEntity

class JokesEntToDomMapper {

    private fun map(ent: JokesEntity): JokeDom {
        return JokeDom(
            id = ent.id,
            joke = ent.joke,
            createdAt = ent.createdAt
        )
    }


    fun mapList(ent: List<JokesEntity>): ArrayList<JokeDom> {

        val result = ArrayList<JokeDom>()

        ent.forEach {
            result.add(map(it))
        }

        return result
    }

}