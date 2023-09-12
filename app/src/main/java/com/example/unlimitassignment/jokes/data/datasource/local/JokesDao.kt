package com.example.unlimitassignment.jokes.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unlimitassignment.jokes.data.entities.local.JokesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(joke: JokesEntity)

    @Query("SELECT * FROM jokes order by id DESC LIMIT 10")
    fun getAllJokes(): Flow<List<JokesEntity>>

    @Query("DELETE FROM jokes where id NOT IN (SELECT id from jokes ORDER BY id DESC LIMIT 10)")
    fun deleteExtraRecords()
}