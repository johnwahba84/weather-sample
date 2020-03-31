package com.sample.openweathermap.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.openweathermap.databinding.WeatherItemBinding
import com.sample.openweathermap.model.weather.WeatherResponse
import com.sample.openweathermap.ui.weather.WeatherAdapter.WeatherViewHolder

class WeatherAdapter(private val weatherList: List<WeatherResponse>?) :
    RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding =
            WeatherItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        return WeatherViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherResponse = weatherList!![position]
        holder.bind(weatherResponse)
    }

    override fun getItemCount(): Int {
        return weatherList?.size ?: 0
    }

    class WeatherViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherResponse?) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

}