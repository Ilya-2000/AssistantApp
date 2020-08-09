package com.impact.assistantapp.network.interfaces

import com.impact.assistantapp.data.model.CurrentWeather
import com.impact.assistantapp.data.model.weather.WeatherData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenWeatherApiService {

    @GET("/data/2.5/weather?q=Orsk&appid=baeb21865bf3956339d3d74e88880343")
    fun getCurrentWeather(
        @Query("city name") city: String,
        @Query("your api key") apiKey: String

    ): Observable<WeatherData>

    companion object {
        fun create(): OpenWeatherApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org")
                .build()
            return retrofit.create(OpenWeatherApiService::class.java)
        }
    }

}