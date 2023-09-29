package com.imdvlpr.weatherappp.model

import android.os.Parcelable
import eu.amirs.JSON
import kotlinx.parcelize.Parcelize
import com.imdvlpr.weatherappp.helper.getDouble
import com.imdvlpr.weatherappp.helper.getInt
import com.imdvlpr.weatherappp.helper.getList
import com.imdvlpr.weatherappp.helper.getString

@Parcelize
data class CurrentForecast(
    var lastUpdate: String = "",
    var tempC: Double = 0.0,
    var tempF: Double = 0.0,
    var conditionName: String = "",
    var conditionIcon: String = "",
    var conditionCode: Int = 0,
    var windKph: Double = 0.0,
    var airPressure: Double = 0.0,
    var humidity: Int = 0,
    var visibilityKm: Double = 0.0,
    var uvIndex: Double = 0.0,
    var airQualityIndex: Int = 0,
    var forecastDay: ArrayList<ForecastDay> = ArrayList(),
    var tzId: String = ""
): Parcelable {
    constructor(data: JSON): this() {
        this.tzId = data.key("location").getString("tz_id")
        this.lastUpdate = data.key("current").getString("last_updated")
        this.tempC = data.key("current").getDouble("temp_c")
        this.tempF = data.key("current").getDouble("temp_f")
        this.conditionName = data.key("current").key("condition").getString("text")
        this.conditionIcon = data.key("current").key("condition").getString("icon").drop(2)
        this.conditionCode = data.key("current").key("condition").getInt("code")
        this.windKph = data.key("current").getDouble("wind_kph")
        this.airPressure = data.key("current").getDouble("pressure_mb")
        this.humidity = data.key("current").getInt("humidity")
        this.visibilityKm = data.key("current").getDouble("vis_km")
        this.uvIndex = data.key("current").getDouble("uv")
        this.airQualityIndex = data.key("current").key("air_quality").getInt("us-epa-index")
        val forecastDay = ArrayList<ForecastDay>()
        data.key("forecast").getList("forecastday").map {
            val forecast = ForecastDay(it)
            forecastDay.add(forecast)
        }
        this.forecastDay = forecastDay
    }
}
