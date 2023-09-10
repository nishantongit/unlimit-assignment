package com.example.unlimitassignment.jokes.presentation.di

import android.content.Context
import com.example.unlimitassignment.jokes.presentation.ui.view.JokeListFragment
import dagger.Component

@Component(modules = [JokesModule::class])
interface JokesComponent {

    fun inject(jokeListFragment: JokeListFragment)

    object Initializer {
        fun init(context: Context): JokesComponent {
            val jokesModule = JokesModule(context)
            jokesModule.context = context
            return DaggerJokesComponent.builder()
                .jokesModule(jokesModule).build()
        }
    }
}