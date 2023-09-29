package com.imdvlpr.weatherappp.model

import android.os.Parcelable
import eu.amirs.JSON
import kotlinx.parcelize.Parcelize
import com.imdvlpr.weatherappp.helper.getString

@Parcelize
data class Astro(
    var sunrise: String = "",
    var sunset: String = ""
): Parcelable {
    constructor(data: JSON): this() {
        this.sunrise = data.getString("sunrise")
        this.sunset = data.getString("sunset")
    }
}
