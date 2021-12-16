package com.example.appsevilla.api

import com.example.appsevilla.model.SitePoi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getPoi (@Url url:String): Response<SitePoi>
}