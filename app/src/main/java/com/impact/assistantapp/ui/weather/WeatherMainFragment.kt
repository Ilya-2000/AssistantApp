package com.impact.assistantapp.ui.weather

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.adapters.WeatherAdapter
import com.impact.assistantapp.data.model.weather.OneCallWeatherData
import com.impact.assistantapp.ui.auth.login.LoginViewModel

class WeatherMainFragment : Fragment() {
    private val TAG = "WeatherMainFragment"
    companion object {
        fun newInstance() = WeatherMainFragment()
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: WeatherMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(WeatherMainViewModel::class.java)
        viewModel.getSevenDaysWeather()
        var current: OneCallWeatherData.Current? = null
        var daily =  mutableListOf<OneCallWeatherData.Daily>()
        //var recyclerView: RecyclerView
        recyclerView = root.findViewById(R.id.weather_main_rv)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.dailyWeather.observe(viewLifecycleOwner, Observer {
            val adapter = WeatherAdapter(viewModel, it)
            recyclerView.adapter = adapter
            Log.d(TAG, "DailyLiveData, $it")
        })

        return root
    }



}