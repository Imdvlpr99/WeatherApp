package com.imdvlpr.weatherappp.helper.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.LayoutCurrentWeatherBinding
import com.imdvlpr.weatherappp.helper.Constants
import com.imdvlpr.weatherappp.helper.SharedPreference
import com.imdvlpr.weatherappp.helper.getCurrentFormattedDate
import com.imdvlpr.weatherappp.helper.isDaytime
import com.imdvlpr.weatherappp.model.CurrentForecast
import java.util.Calendar
import java.util.TimeZone

class CurrentWeatherLayout: ConstraintLayout {

    private lateinit var binding: LayoutCurrentWeatherBinding
    private lateinit var sessionManager: SharedPreference

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        binding = LayoutCurrentWeatherBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_current_weather, this, true))
        sessionManager = SharedPreference()
        sessionManager.sessionManager(context)
        initView()
    }

    private fun initView() {
        binding.todayDate.text = getCurrentFormattedDate()
    }

    fun setWeatherData(data: CurrentForecast) {
        binding.weatherName.text = data.conditionName
        binding.weatherDegree.text = "${data.tempC}°"
        if (context.isDaytime()) {
            when(data.conditionCode) {
                Constants.WEATHER_CODE.HEAVY_RAIN_SHOWER,
                Constants.WEATHER_CODE.HEAVY_RAIN -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heavy_rain))
                Constants.WEATHER_CODE.SUNNY -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_sunny))
                Constants.WEATHER_CODE.PARTLY_CLOUDY -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_partly_cloud))
                Constants.WEATHER_CODE.OVERCAST -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cloudy))
                Constants.WEATHER_CODE.CLOUDY,
                Constants.WEATHER_CODE.MIST,
                Constants.WEATHER_CODE.FOG -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fog))
                Constants.WEATHER_CODE.PATCHY_RAIN_POSSIBLE,
                Constants.WEATHER_CODE.PATCHY_LIGHT_RAIN,
                Constants.WEATHER_CODE.LIGHT_DRIZZLE,
                Constants.WEATHER_CODE.LIGHT_RAIN,
                Constants.WEATHER_CODE.LIGHT_RAIN_SHOWER,
                Constants.WEATHER_CODE.MODERATE_RAIN,
                Constants.WEATHER_CODE.MODERATE_RAIN_AT_TIMES -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_drizzle))
                Constants.WEATHER_CODE.THUNDERY_OUTBREAKS_POSSIBLE,
                Constants.WEATHER_CODE.HEAVY_RAIN_WITH_THUNDER,
                Constants.WEATHER_CODE.LIGHT_RAIN_WITH_THUNDER -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_rain_thunder))
            }
        } else {
            when(data.conditionCode) {
                Constants.WEATHER_CODE.HEAVY_RAIN_SHOWER,
                Constants.WEATHER_CODE.HEAVY_RAIN -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heavy_rain))
                Constants.WEATHER_CODE.SUNNY -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_clear))
                Constants.WEATHER_CODE.PARTLY_CLOUDY -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_partly_cloudy_night))
                Constants.WEATHER_CODE.OVERCAST -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_cloudy))
                Constants.WEATHER_CODE.CLOUDY,
                Constants.WEATHER_CODE.MIST,
                Constants.WEATHER_CODE.FOG -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_fog))
                Constants.WEATHER_CODE.PATCHY_RAIN_POSSIBLE,
                Constants.WEATHER_CODE.PATCHY_LIGHT_RAIN,
                Constants.WEATHER_CODE.LIGHT_DRIZZLE,
                Constants.WEATHER_CODE.LIGHT_RAIN,
                Constants.WEATHER_CODE.LIGHT_RAIN_SHOWER,
                Constants.WEATHER_CODE.MODERATE_RAIN,
                Constants.WEATHER_CODE.MODERATE_RAIN_AT_TIMES -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_drizzle))
                Constants.WEATHER_CODE.THUNDERY_OUTBREAKS_POSSIBLE,
                Constants.WEATHER_CODE.HEAVY_RAIN_WITH_THUNDER,
                Constants.WEATHER_CODE.LIGHT_RAIN_WITH_THUNDER -> binding.weaterIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_rain_thunder))
            }
        }
    }
}