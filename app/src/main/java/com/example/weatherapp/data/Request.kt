package com.example.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Request(

    @SerializedName("type")
    val type: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("unit")
    val unit: String
)