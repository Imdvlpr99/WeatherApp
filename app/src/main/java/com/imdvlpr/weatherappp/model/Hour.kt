package com.imdvlpr.weatherappp.model

import android.os.Parcelable
import eu.amirs.JSON
import kotlinx.parcelize.Parcelize
import com.imdvlpr.weatherappp.helper.getDouble
import com.imdvlpr.weatherappp.helper.getInt
import com.imdvlpr.weatherappp.helper.getString

@Parcelize
data class Hour(
    var time: String = "",
    var tempC: Double = 0.0,
    var conditionName: String = "",
    var conditionIcon: String = "",
    var conditionCode: Int = 0
): Parcelable {
    constructor(data: JSON): this() {
        this.time = data.getString("time")
        this.tempC = data.getDouble("temp_c")
        this.conditionName = data.key("condition").getString("text")
        this.conditionIcon = data.key("condition").getString("icon")
        this.conditionCode = data.key("condition").getInt("code")
    }
}
