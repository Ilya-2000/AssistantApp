package com.impact.assistantapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.CurrentWeather
import com.impact.assistantapp.network.interfaces.OpenWeatherApiService
import com.impact.assistantapp.utils.NetworkData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val openWeatherApiService by lazy {
        OpenWeatherApiService.create()
    }
    private val networkData = NetworkData()
    private val city = "Orsk"


    fun getCurrentWeather() {

        /*openWeatherApiService.getCurrentWeather(city, networkData.weatherApiKey.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map()
            .subscribe()*/
    }
}