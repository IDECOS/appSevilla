package com.example.appsevilla.api


import com.example.appsevilla.model.SitePoi
import retrofit2.http.GET


interface ApiService {
    @GET("posts")
    suspend fun requestPoi(): List<SitePoi>

}