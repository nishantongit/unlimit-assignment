package com.example.unlimitassignment.jokes.data.datasource.local

import com.example.unlimitassignment.jokes.data.entities.local.JokesEntity
import kotlinx.coroutines.flow.Flow

class JokesLDSImpl(private val jokesDao: JokesDao) : JokesLDS {

    override suspend fun getJokes(): Flow<List<JokesEntity>> {
        return jokesDao.getAllJokes()
    }

    override suspend fun insert(joke: JokesEntity) {
        jokesDao.insert(joke)
    }

    override suspend fun deleteExtraRecords() {
        jokesDao.deleteExtraRecords()
    }
}