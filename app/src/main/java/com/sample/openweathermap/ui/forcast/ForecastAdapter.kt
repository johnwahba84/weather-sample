package com.sample.openweathermap.ui.forcast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.openweathermap.databinding.ForecastItemBinding
import com.sample.openweathermap.domain.model.forecast.ForecastResponse

class ForecastAdapter(private val forecastList: List<ForecastResponse.Forecast>?) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding =
            ForecastItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        return ForecastViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastResponse = forecastList!![position]
        holder.bind(forecastResponse)
    }

    override fun getItemCount(): Int {
        return forecastList?.size ?: 0
    }

    class ForecastViewHolder(private val binding: ForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastResponse.Forecast?) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

}