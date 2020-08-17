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
import com.impact.assistantapp.data.model.weather.OneCallWeatherData
import com.impact.assistantapp.data.model.weather.WeatherData
import com.impact.assistantapp.databinding.CurrentBinding

import com.impact.assistantapp.ui.weather.WeatherMainViewModel
import com.squareup.picasso.Picasso

class WeatherAdapter (private var viewModel: WeatherMainViewModel, private var dailyList: MutableList<OneCallWeatherData.Daily>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item_daily, parent, false)
            return DailyWeatherHolder(view)
        } else {
            throw RuntimeException("View creating has been failed")
        }
    }



    override fun getItemCount(): Int = dailyList.size

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
        val item = dailyList[position]
        holder.bind(item)
    }

    inner class CurrentWeatherHolder(val currentBinding: CurrentBinding) : RecyclerView.ViewHolder(currentBinding.root) {
        /*private val currentTempText: TextView = itemView.findViewById<TextView>(R.id.current_temp_weather_main_card)
        private val currentWeatherImg = itemView.findViewById<ImageView>(R.id.weather_current_img)*/

        fun bind(item: WeatherMainViewModel) {
            this.currentBinding.weatherMainViewModel = item
            currentBinding.executePendingBindings()
            /*currentTempText.text = item.temp.toString()
            Picasso.get()
                .load(item.weather[0].icon)
                .into(currentWeatherImg)*/

        }
    }

    inner class DailyWeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tempText = itemView.findViewById<TextView>(R.id.temp_daily_card_text)
        private val weatherImg = itemView.findViewById<ImageView>(R.id.daily_card_img)
        private val sunriseText = itemView.findViewById<TextView>(R.id.sunrise_daily_card_text)
        private val sunsetText = itemView.findViewById<TextView>(R.id.sunset_daily_card_text)
        ///
        private val pressureText = itemView.findViewById<TextView>(R.id.pressure_daily_card_text)
        private val windSpeedText = itemView.findViewById<TextView>(R.id.wind_speed_daily_card_text)
        private val windDegText = itemView.findViewById<TextView>(R.id.wind_deg_daily_card_text)
        private val popText = itemView.findViewById<TextView>(R.id.pop_daily_card_text)
        private val rainText = itemView.findViewById<TextView>(R.id.rain_daily_card_text)
        private val snowText = itemView.findViewById<TextView>(R.id.snow_daily_card_text)
        ///
        private val tempMorningText = itemView.findViewById<TextView>(R.id.temp_daily_morning_text)
        private val tempDayText = itemView.findViewById<TextView>(R.id.temp_daily_day_text)
        private val tempEveningText = itemView.findViewById<TextView>(R.id.temp_daily_evening_text)
        private val tempNightText = itemView.findViewById<TextView>(R.id.temp_daily_night_text)

        fun bind(item: OneCallWeatherData.Daily) {



        }
    }
}