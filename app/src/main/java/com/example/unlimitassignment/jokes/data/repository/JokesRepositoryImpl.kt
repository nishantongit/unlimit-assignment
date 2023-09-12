package com.example.unlimitassignment.jokes.data.repository

import com.example.unlimitassignment.jokes.data.datasource.local.JokesLDS
import com.example.unlimitassignment.jokes.data.datasource.remote.JokesRDS
import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import com.example.unlimitassignment.jokes.data.mapper.JokesDtoToEntMapper
import com.example.unlimitassignment.jokes.data.mapper.JokesEntToDomMapper
import com.example.unlimitassignment.jokes.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JokesRepositoryImpl(private val rds: JokesRDS, private val lds: JokesLDS, private val dtoToEntMapper: JokesDtoToEntMapper, private val entToDomMapper: JokesEntToDomMapper) :
    JokesRepository {

    override suspend fun fetchJokeFromRemote() {
            val result = rds.fetchJokes()
            if (result.isSuccessful) {
                //save to DB
                result.body()?.let {
                    if(it.joke?.isNotBlank() == true){
                        lds.insert(dtoToEntMapper.map(it))
                        lds.deleteExtraRecords()
                    }else{
                        //report to analytics or UI as required
                    }
                }
            }
        }


    override suspend fun getJokesFromLocal(): Flow<ArrayList<JokeDom>> {
        return lds.getJokes().map {
            entToDomMapper.mapList(it)
        }
    }

}