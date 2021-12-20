package com.example.appsevilla.api

import com.example.appsevilla.model.SitePoi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class RetrofitFactory {

    companion object {

        fun getRetrofitFactory(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private const val BASE_URL = "https://my-json-server.typicode.com/IDECOS/dbapi"
    }


}