package com.impact.assistantapp.ui.weather

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.impact.assistantapp.R

class WeatherMainFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherMainFragment()
    }

    private lateinit var viewModel: WeatherMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}