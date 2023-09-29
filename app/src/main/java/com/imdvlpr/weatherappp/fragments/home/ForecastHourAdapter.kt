package com.imdvlpr.weatherappp.fragments.home

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.ItemWeatherHourBinding
import com.imdvlpr.weatherappp.helper.Constants
import com.imdvlpr.weatherappp.helper.getCurrentHour
import com.imdvlpr.weatherappp.helper.getCurrentTheme
import com.imdvlpr.weatherappp.helper.isDaytime
import com.imdvlpr.weatherappp.model.Hour
import java.text.SimpleDateFormat

class ForecastHourAdapter(var context: Context, var data: ArrayList<Hour>): RecyclerView.Adapter<ForecastHourAdapter.ForecastHourViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHourViewHolder {
        return ForecastHourViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather_hour, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ForecastHourViewHolder, position: Int) {
        holder.bind(context, data[position])
    }

    inner class ForecastHourViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, item: Hour) = with(itemView) {
            val binding = ItemWeatherHourBinding.bind(itemView)
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val outputFormat = SimpleDateFormat("HH:mm")
            val outputFormatHour = SimpleDateFormat("HH")
            val date = inputFormat.parse(item.time)

            binding.weatherDegree.text = "${item.tempC}°"
            binding.weatherHour.text = outputFormat.format(date)

            val listHour = ArrayList<Int>()
            listHour.add(outputFormatHour.format(date).toInt())

            if (outputFormatHour.format(date) == getCurrentHour()) {
                if (context.getCurrentTheme()) {
                    binding.itemCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                    binding.weatherHour.setTextColor(ContextCompat.getColor(context, R.color.white))
                    binding.weatherDegree.setTextColor(ContextCompat.getColor(context, R.color.white))
                } else {
                    binding.itemCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                    binding.weatherHour.setTextColor(ContextCompat.getColor(context, R.color.white))
                    binding.weatherDegree.setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            } else {
                if (context.getCurrentTheme()) {
                    binding.itemCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.blackSoft))
                } else {
                    binding.itemCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                }
            }

            for (value in listHour) {
                when (value) {
                    in 0..5,
                    in 19..23-> {
                        when(item.conditionCode) {
                            Constants.WEATHER_CODE.HEAVY_RAIN_SHOWER,
                            Constants.WEATHER_CODE.HEAVY_RAIN -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_heavy_rain))

                            Constants.WEATHER_CODE.SUNNY -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_clear))

                            Constants.WEATHER_CODE.PARTLY_CLOUDY -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_partly_cloudy_night))

                            Constants.WEATHER_CODE.OVERCAST -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_cloudy))

                            Constants.WEATHER_CODE.CLOUDY,
                            Constants.WEATHER_CODE.MIST,
                            Constants.WEATHER_CODE.FOG -> binding.weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fog))

                            Constants.WEATHER_CODE.PATCHY_RAIN_POSSIBLE,
                            Constants.WEATHER_CODE.PATCHY_LIGHT_RAIN,
                            Constants.WEATHER_CODE.LIGHT_DRIZZLE,
                            Constants.WEATHER_CODE.LIGHT_RAIN,
                            Constants.WEATHER_CODE.LIGHT_RAIN_SHOWER,
                            Constants.WEATHER_CODE.MODERATE_RAIN,
                            Constants.WEATHER_CODE.MODERATE_RAIN_AT_TIMES -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_drizzle))

                            Constants.WEATHER_CODE.THUNDERY_OUTBREAKS_POSSIBLE,
                            Constants.WEATHER_CODE.HEAVY_RAIN_WITH_THUNDER,
                            Constants.WEATHER_CODE.LIGHT_RAIN_WITH_THUNDER -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_rain_thunder))
                        }
                    }
                    in 6..18 -> {
                        when(item.conditionCode) {
                            Constants.WEATHER_CODE.HEAVY_RAIN_SHOWER,
                            Constants.WEATHER_CODE.HEAVY_RAIN -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_heavy_rain))

                            Constants.WEATHER_CODE.SUNNY -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_sunny))

                            Constants.WEATHER_CODE.PARTLY_CLOUDY -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_partly_cloud))

                            Constants.WEATHER_CODE.OVERCAST -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_cloudy))

                            Constants.WEATHER_CODE.CLOUDY,
                            Constants.WEATHER_CODE.MIST,
                            Constants.WEATHER_CODE.FOG -> binding.weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fog))

                            Constants.WEATHER_CODE.PATCHY_RAIN_POSSIBLE,
                            Constants.WEATHER_CODE.PATCHY_LIGHT_RAIN,
                            Constants.WEATHER_CODE.LIGHT_DRIZZLE,
                            Constants.WEATHER_CODE.LIGHT_RAIN,
                            Constants.WEATHER_CODE.LIGHT_RAIN_SHOWER,
                            Constants.WEATHER_CODE.MODERATE_RAIN,
                            Constants.WEATHER_CODE.MODERATE_RAIN_AT_TIMES -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_drizzle))

                            Constants.WEATHER_CODE.THUNDERY_OUTBREAKS_POSSIBLE,
                            Constants.WEATHER_CODE.HEAVY_RAIN_WITH_THUNDER,
                            Constants.WEATHER_CODE.LIGHT_RAIN_WITH_THUNDER -> binding.weatherIcon.setImageDrawable(
                                ContextCompat.getDrawable(context, R.drawable.ic_rain_thunder))
                        }
                    }
                }
            }
        }
    }
}