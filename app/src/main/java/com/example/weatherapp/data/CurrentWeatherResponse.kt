package com.example.weatherapp.data
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(

    @SerializedName("request")
    val request: Request,
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current
)