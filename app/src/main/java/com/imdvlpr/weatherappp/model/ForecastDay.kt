package com.imdvlpr.weatherappp.model

import android.os.Parcelable
import com.imdvlpr.weatherappp.helper.getList
import eu.amirs.JSON
import kotlinx.parcelize.Parcelize
import com.imdvlpr.weatherappp.helper.getString

@Parcelize
data class ForecastDay(
    var date: String = "",
    var day: Day = Day(),
    var astro: Astro = Astro(),
    var hour: ArrayList<Hour> = ArrayList()

): Parcelable {
    constructor(data: JSON): this() {
        this.date = data.getString("date")
        this.day = Day(data.key("day"))
        this.astro = Astro(data.key("astro"))
        val forecastHour = ArrayList<Hour>()
        data.getList("hour").map {
            val hour = Hour(it)
            forecastHour.add(hour)
        }
        this.hour = forecastHour
    }
}
