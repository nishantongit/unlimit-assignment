package com.example.unlimitassignment.jokes.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    companion object {
        fun getService(): JokesService {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(logging)


            return Retrofit.Builder()
                .baseUrl("https://geek-jokes.sameerkumar.website/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build()).build()
                .create(JokesService::class.java)
        }
    }
}
