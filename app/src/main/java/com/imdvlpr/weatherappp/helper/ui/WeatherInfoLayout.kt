package com.imdvlpr.weatherappp.helper.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.LayoutWeatherInfoBinding
import com.imdvlpr.weatherappp.model.CurrentForecast

class WeatherInfoLayout: ConstraintLayout {

    private lateinit var binding: LayoutWeatherInfoBinding

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
        binding = LayoutWeatherInfoBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_weather_info, this, true))
    }

    fun setWeatherInfo(data: CurrentForecast) {
        binding.windValue.text = "${data.windKph}km/h"
        binding.humidityValue.text = "${data.humidity}%"
        binding.visibilityValue.text = "${data.visibilityKm}km"
    }
}