package com.impact.assistantapp.network.interfaces

import com.impact.assistantapp.data.model.CurrentWeather
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenWeatherApiService {

    @GET("/data/2.5/weather?q={city}&appid={key}")
    fun getCurrentWeather(
       @Path("city") city: String,
       @Path("key") key: String
    ): Observable<CurrentWeather>

    companion object {
        fun create(): OpenWeatherApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("api.openweathermap.org")
                .build()
            return retrofit.create(OpenWeatherApiService::class.java)
        }
    }

}