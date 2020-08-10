package com.impact.assistantapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.weather.WeatherData
import com.impact.assistantapp.network.interfaces.OpenWeatherApiService
import com.impact.assistantapp.utils.NetworkData
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"
    private val openWeatherApiService by lazy {
        OpenWeatherApiService.create()
    }
    private val networkData = NetworkData()
    private val city = "Orsk"

    private val _currentWeather = MutableLiveData<WeatherData.Main>()
    val currentWeather: LiveData<WeatherData.Main>
        get() = _currentWeather

    fun setCurrentWeather(data: WeatherData.Main) {
        _currentWeather.value = data
    }


    fun getCurrentWeather() {

        openWeatherApiService.getCurrentWeather(city,"baeb21865bf3956339d3d74e88880343","metric")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherData>{
                override fun onComplete() {
                    Log.d(TAG, "getCurrentWeather/onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "getCurrentWeather/onSubscribe: $d")
                }

                override fun onNext(t: WeatherData) {
                    Log.d(TAG, "getCurrentWeather/onNext: ${t.coord}")

                    setCurrentWeather(t.main)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getCurrentWeather/onError: $e")
                }

            })
    }
}