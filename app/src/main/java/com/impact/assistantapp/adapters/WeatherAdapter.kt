package com.impact.assistantapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.data.model.weather.CurrentWeather
import com.impact.assistantapp.data.model.weather.DailyWeather
import com.impact.assistantapp.data.model.weather.OneCallWeatherData
import com.impact.assistantapp.data.model.weather.WeatherData
import com.impact.assistantapp.databinding.CurrentBinding
import com.impact.assistantapp.databinding.DailyBinding

import com.impact.assistantapp.ui.weather.WeatherMainViewModel
import com.squareup.picasso.Picasso

class WeatherAdapter (private var viewModel: WeatherMainViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         //ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_item_current, parent, false))
        //var v: ViewHolder? = null
        if (viewType == 0) {
            //val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item_current, parent, false)
            val layoutInflater = LayoutInflater.from(parent.context)
            val view: CurrentBinding = DataBindingUtil.inflate(layoutInflater, R.layout.weather_item_current, parent, false)
            return CurrentWeatherHolder(view)
        } else if (viewType == 1) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view: DailyBinding = DataBindingUtil.inflate(layoutInflater, R.layout.weather_item_daily, parent, false)
            return DailyWeatherHolder(view)
        } else {
            throw RuntimeException("View creating has been failed")
        }
    }



    override fun getItemCount(): Int = viewModel.weatherCount

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            0 -> initCurrentWeather(holder as CurrentWeatherHolder, position)
            1 -> initDailyWeather(holder as DailyWeatherHolder, position)
        }
    }

    private fun initCurrentWeather(holder: CurrentWeatherHolder, position: Int) {
        val item = viewModel
        holder.bind(item)
    }

    private fun initDailyWeather(holder: DailyWeatherHolder, position: Int) {
        val item = viewModel.dailyWeather.value?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class CurrentWeatherHolder(val currentBinding: CurrentBinding) : RecyclerView.ViewHolder(currentBinding.root) {

        fun bind(item: WeatherMainViewModel) {
            this.currentBinding.weatherMainViewModel = item
            currentBinding.executePendingBindings()
            Picasso.get()
                .load(item.icon)
                .into(currentBinding.weatherCurrentImg)


        }
    }

    inner class DailyWeatherHolder(val dailyBinding: DailyBinding) : RecyclerView.ViewHolder(dailyBinding.root) {

        fun bind(item: OneCallWeatherData.Daily) {
            this.dailyBinding.dailyWeather = item
            Picasso.get()
                .load("http://openweathermap.org/img/wn/${item.weather[0].icon}"+"@2x.png")
                .into(dailyBinding.dailyCardImg)



        }
    }
}