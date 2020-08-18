package com.impact.assistantapp.ui.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.R
import com.impact.assistantapp.data.model.weather.OneCallWeatherData
import com.impact.assistantapp.network.interfaces.OpenWeatherApiService
import com.impact.assistantapp.utils.NetworkData
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class WeatherMainViewModel : ViewModel() {
    private val TAG = "WeatherMainViewModel"
    private val openWeatherApiService by lazy {
        OpenWeatherApiService.create()
    }
    private val networkData = NetworkData()
    private val city = "Orsk"
    var icon: String = ""
    val weatherCount = 8



    private val _currentWeather = MutableLiveData<OneCallWeatherData.Current>()
    val currentWeather: LiveData<OneCallWeatherData.Current>
        get() = _currentWeather

    private val _dailyWeather = MutableLiveData<MutableList<OneCallWeatherData.Daily>>()
    val dailyWeather: LiveData<MutableList<OneCallWeatherData.Daily>>
        get() = _dailyWeather

    private val _currentTemperature = MutableLiveData<String>()
    val currentTemperature: LiveData<String>
        get() = _currentTemperature

    fun setCurrentWeather(data: OneCallWeatherData.Current) {
        _currentWeather.value = data
        Log.d(TAG, "setCurrentWeather ${_currentWeather.value}")

    }

    private val _dailyIcon = MutableLiveData<String>()
    val dailyIcon: LiveData<String>
        get() = _dailyIcon





    fun setDailyIcon(data: String) {
        _dailyIcon.value = data
    }


    fun setCurrentIcon(data: String) {
        icon = data
    }

    fun loadImage(view: ImageView, url: String) {
        Picasso.get()
            .load(url)
            .into(view)
    }

    fun setCurrentTemperature(data: String) {
        _currentTemperature.value = data
        Log.d(TAG, "setCurrentWeather ${_currentTemperature.value}")

    }



    fun setDailyWeather(data: List<OneCallWeatherData.Daily>) {
        _dailyWeather.value = data.toMutableList()
        Log.d(TAG, "setDailyWeather ${_dailyWeather.value}")
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
                    Log.d(TAG, "getSevenDaysWeather/onNext/current: ${t.current.temp}")
                    setCurrentTemperature(_currentWeather.value?.temp.toString())
                    Log.d(TAG, "getSevenDaysWeather/onNext/currentTemp: ${_currentTemperature.value.toString()}")
                    setCurrentIcon("http://openweathermap.org/img/wn/${t.current.weather[0].icon}"+"@2x.png")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getSevenDaysWeather/onError: $e")
                }

            })

    }
}