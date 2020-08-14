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

    private lateinit var viewModel: WeatherMainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter
    private var current: OneCallWeatherData.Current? = null
    private var daily: MutableList<OneCallWeatherData.Daily>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherMainViewModel::class.java)
        viewModel.getSevenDaysWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_main_fragment, container, false)

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            current = it
        })
        viewModel.dailyWeather.observe(viewLifecycleOwner, Observer {
            daily = it
        })
        val a = current
        val b = daily
        recyclerView = root.findViewById(R.id.weather_main_rv)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        Log.d(TAG, "Current is ${current?.feelsLike}")
        if (a != null && !b.isNullOrEmpty()) {
            weatherAdapter = WeatherAdapter(a,b)
            Log.d(TAG, a.toString() +" " + b.size.toString())
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}