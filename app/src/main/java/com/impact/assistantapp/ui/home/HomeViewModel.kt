package com.impact.assistantapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.impact.assistantapp.data.model.finance.Currency
import com.impact.assistantapp.data.model.weather.WeatherData
import com.impact.assistantapp.network.interfaces.CurrencyApiService
import com.impact.assistantapp.network.interfaces.OpenWeatherApiService
import com.impact.assistantapp.utils.NetworkData
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.math.RoundingMode
import java.text.DecimalFormat

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"
    private val openWeatherApiService by lazy {
        OpenWeatherApiService.create()
    }

    private val currencyApiService by lazy {
        CurrencyApiService.create()
    }
    private val networkData = NetworkData()
    private val city = "Orsk"

    private val _currentWeather = MutableLiveData<WeatherData>()
    val currentWeather: LiveData<WeatherData>
        get() = _currentWeather

    private val _currency = MutableLiveData<Currency>()
    val currency: LiveData<Currency>
        get() = _currency

    fun setCurrentWeather(data: WeatherData) {
        _currentWeather.value = data
    }

    fun setCurrency(data: Currency) {
        val a = data._data.dollarToRuble.toDouble()
        val b = data._data.euroToRuble.toDouble()
        val dollar = String.format("%.2f",a)
        val euro = String.format("%.2f",b)
        data._data.dollarToRuble = dollar
        data._data.euroToRuble = euro
        _currency.value = data
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

                    setCurrentWeather(t)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getCurrentWeather/onError: $e")
                }

            })
    }

    fun getCurrency() {
        currencyApiService.getCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Currency> {
                override fun onComplete() {
                    Log.d(TAG, "getCurrency/onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "getCurrency/onSubscribe: $d")
                }

                override fun onNext(t: Currency) {
                    Log.d(TAG, "getCurrency/onNext: ${t.message}")
                    val df = DecimalFormat("#.##")
                    df.roundingMode = RoundingMode.CEILING
                    /*val c = df.format(t._data.dollarToRuble)
                    val d = df.format(t._data.euroToRuble)*/

                    _currency.value = t

                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getCurrency/onError: $e")
                }

            })
    }


}