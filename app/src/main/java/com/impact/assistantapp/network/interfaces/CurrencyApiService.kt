package com.impact.assistantapp.network.interfaces

import com.impact.assistantapp.data.model.finance.Currency
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CurrencyApiService {

    @GET("/api/?get=rates&pairs=USDRUB,EURRUB&key=59b55763cb4ec7f9cd553fc018fc5265")
    fun getCurrency(): Observable<Currency>

    companion object {
        fun create(): CurrencyApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://currate.ru")
                .build()
            return retrofit.create(CurrencyApiService::class.java)
        }
    }
}