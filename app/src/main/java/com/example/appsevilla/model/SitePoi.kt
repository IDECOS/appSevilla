package com.example.appsevilla.model

import com.google.gson.annotations.SerializedName


data class SitePoi(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("geo")
    var geo: String,
    @SerializedName("temperature")
    var temperature: String,
    @SerializedName("qualification")
    var qualification: Int

    )
