package com.example.unlimitassignment.jokes.presentation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unlimitassignment.jokes.data.entities.local.JokeDom
import com.example.unlimitassignment.jokes.domain.usecase.GetJokes
import kotlinx.coroutines.*

class JokesViewModel(private val getJokes: GetJokes) : ViewModel() {

    val jokeLive = MutableLiveData<ArrayList<JokeDom>>()
    private var jobRunning = true

    fun onLoad() {
    }

     suspend fun loadFromLocal() {
        getJokes.getFromLocal().collect {
            jokeLive.postValue(it)
        }
    }

    private suspend fun loadFromRemote() {
        getJokes.invoke()
    }


    suspend fun startRepeatingJob(timeInterval: Long) {
            while (jobRunning) {
                loadFromRemote()
                delay(timeInterval)
            }
    }




}