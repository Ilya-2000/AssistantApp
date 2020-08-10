package com.impact.assistantapp.ui.weather

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.adapters.WeatherAdapter

class WeatherMainFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherMainFragment()
    }

    private lateinit var viewModel: WeatherMainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_main_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(WeatherMainViewModel::class.java)
        viewModel.getSevenDaysWeather()
        recyclerView = root.findViewById(R.id.weather_main_rv)
        weatherAdapter = WeatherAdapter(viewModel)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}