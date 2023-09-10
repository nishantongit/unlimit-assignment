package com.example.unlimitassignment.base.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unlimitassignment.jokes.data.datasource.local.JokesDao
import com.example.unlimitassignment.jokes.data.entities.local.JokesEntity

@Database(entities = [JokesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao

    companion object {
        private const val DB_NAME = "unlimit"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: run {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, DB_NAME
                ).build()
                return INSTANCE!!
            }
        }
    }
}
