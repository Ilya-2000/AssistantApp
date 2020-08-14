package com.impact.assistantapp.ui.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.weather.OneCallWeatherData
import com.impact.assistantapp.data.model.weather.WeatherData
import com.impact.assistantapp.network.interfaces.OpenWeatherApiService
import com.impact.assistantapp.utils.NetworkData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherMainViewModel : ViewModel() {
    private val TAG = "WeatherMainViewModel"
    private val openWeatherApiService by lazy {
        OpenWeatherApiService.create()
    }
    private val networkData = NetworkData()
    private val city = "Orsk"

    private val _currentWeather = MutableLiveData<OneCallWeatherData.Current>()
    val currentWeather: LiveData<OneCallWeatherData.Current>
        get() = _currentWeather

    private val _dailyWeather = MutableLiveData<MutableList<OneCallWeatherData.Daily>>()
    val dailyWeather: LiveData<MutableList<OneCallWeatherData.Daily>>
        get() = _dailyWeather

    fun setCurrentWeather(data: OneCallWeatherData.Current) {
        _currentWeather.value = data

    }



    fun setDailyWeather(data: List<OneCallWeatherData.Daily>) {
        _dailyWeather.value = data.toMutableList()
    }

    fun getSevenDaysWeather() {
        openWeatherApiService.getSevenDaysWeather(51.2, 58.57,"7d",
            "baeb21865bf3956339d3d74e88880343", "metric")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<OneCallWeatherData>{
                override fun onComplete() {
                    Log.d(TAG, "getSevenDaysWeather/onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "getSevenDaysWeather/onSubscribe: $d")
                }

                override fun onNext(t: OneCallWeatherData) {
                    Log.d(TAG, "getSevenDaysWeather/onNext: ${t.lat}")
                    setCurrentWeather(t.current)
                    setDailyWeather(t.daily)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getSevenDaysWeather/onError: $e")
                }

            })

    }
}