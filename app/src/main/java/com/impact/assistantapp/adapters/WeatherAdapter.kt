package com.impact.assistantapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.impact.assistantapp.R
import com.impact.assistantapp.ui.weather.WeatherMainViewModel

class WeatherAdapter (weatherViewModel: WeatherMainViewModel): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_item_current, parent, false))


    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}