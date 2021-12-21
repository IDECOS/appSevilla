package com.example.appsevilla.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {

        fun getRetrofitFactory(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private const val BASE_URL = "https://my-json-server.typicode.com/IDECOS/dbapi/"
    }


}