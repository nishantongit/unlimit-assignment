package com.example.unlimitassignment.base.di

import com.example.unlimitassignment.UnlimitApplication
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(unlimitApplication: UnlimitApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(unlimitApplication: UnlimitApplication): Builder
        fun build(): AppComponent
    }
}