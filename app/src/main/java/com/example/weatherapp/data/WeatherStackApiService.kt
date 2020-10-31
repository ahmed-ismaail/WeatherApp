package com.example.weatherapp.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "772ffdf65202975fb336a03e9daab751"
const val BASE_URL = "http://api.weatherstack.com/"

//http://api.weatherstack.com/current?access_key=772ffdf65202975fb336a03e9daab751&query=london
interface WeatherStackApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String
    ):Deferred<CurrentWeatherResponse>

    companion object {
        //this function is called by just writing WeatherStackApiService()
        operator fun invoke(): WeatherStackApiService {
            //use interceptor so you don't have to add @Query("access_key")
            //to every request to WeatherStackApi
            //you only use it once in the interceptor
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherStackApiService::class.java)
        }
    }
}