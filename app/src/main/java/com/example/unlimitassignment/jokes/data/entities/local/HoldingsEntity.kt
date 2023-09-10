package com.example.unlimitassignment.jokes.data.entities.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "jokes",
    indices = [
        Index(value = ["id"]),
        Index(value = ["joke"], unique = true)
    ]
)

data class JokesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo("joke")
    var joke: String? = null,

     @ColumnInfo(name = "created_at")
    val createdAt: Long
)