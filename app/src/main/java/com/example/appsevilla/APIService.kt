package com.example.appsevilla

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    fun getPoi (@Url url:String): Response<SitePoi>
}