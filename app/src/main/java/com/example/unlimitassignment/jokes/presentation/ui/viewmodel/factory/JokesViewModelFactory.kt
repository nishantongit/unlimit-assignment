package com.example.unlimitassignment.jokes.presentation.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unlimitassignment.jokes.domain.usecase.GetJokes
import com.example.unlimitassignment.jokes.presentation.ui.viewmodel.JokesViewModel

class JokesViewModelFactory(private val getJokes: GetJokes) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(JokesViewModel::class.java) -> JokesViewModel(
                getJokes
            ) as T
            else -> throw java.lang.IllegalArgumentException("No ViewModel Found")
        }
    }
}