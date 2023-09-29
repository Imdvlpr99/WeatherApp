package com.imdvlpr.weatherappp.model

import android.os.Parcelable
import eu.amirs.JSON
import kotlinx.parcelize.Parcelize
import com.imdvlpr.weatherappp.helper.getDouble
import com.imdvlpr.weatherappp.helper.getInt
import com.imdvlpr.weatherappp.helper.getString

@Parcelize
data class Day(
    var maxTemp: Double = 0.0,
    var minTemp: Double = 0.0,
    var avgTemp: Double = 0.0,
    var conditionName: String = "",
    var conditionIcon: String = "",
    var conditionCode: Int = 0,
    var uv: Double = 0.0,
    var airQualityIndex: Int = 0
): Parcelable {
    constructor(data: JSON): this() {
        this.maxTemp = data.getDouble("maxtemp_c")
        this.minTemp = data.getDouble("mintemp_c")
        this.avgTemp = data.getDouble("avgtemp_c")
        this.conditionName = data.key("condition").getString("text")
        this.conditionIcon = data.key("condition").getString("icon").drop(2)
        this.conditionCode = data.key("condition").getInt("code")
        this.uv = data.getDouble("uv")
        this.airQualityIndex = data.key("air_quality").getInt("us-epa-index")
    }
}
