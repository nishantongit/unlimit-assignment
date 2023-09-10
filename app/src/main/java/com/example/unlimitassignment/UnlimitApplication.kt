package com.example.unlimitassignment

import androidx.multidex.MultiDexApplication

class UnlimitApplication : MultiDexApplication() {

    companion object;

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    fun getInstance(): UnlimitApplication {
        return this
    }

    fun inject() {

    }

}