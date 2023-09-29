package com.imdvlpr.weatherappp.helper

import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

private lateinit var sessionManager: SharedPreference

fun getCurrentFormattedDate(): String {
    val currentDate = Date()
    val pattern = "EEEE, dd MMMM"
    val sdf = SimpleDateFormat(pattern)
    return sdf.format(currentDate)
}

fun getCurrentHour(): String {
    val currentDate = Date()
    val pattern = "HH"
    val sdf = SimpleDateFormat(pattern)
    return sdf.format(currentDate)
}

fun Context.getCurrentTheme(): Boolean {
    val uiModeManager = this.getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
    val currentNightMode = uiModeManager.nightMode
    return currentNightMode == AppCompatDelegate.MODE_NIGHT_YES
}

fun convertDateToHour(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val outputFormatHour = SimpleDateFormat("HH")
    val hour = inputFormat.parse(date)
    return outputFormatHour.format(hour)
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
    val win: Window = activity.window
    val winParams: WindowManager.LayoutParams = win.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
}

fun Context.isDaytime(): Boolean {
    sessionManager = SharedPreference()
    sessionManager.sessionManager(this)
    val timeZone = TimeZone.getTimeZone(sessionManager.getStringFromPref(Constants.PREF.TIMEZONE_ID))
    val currentTime = Calendar.getInstance(timeZone)

    // Define the time for sunrise and sunset in GMT
    val sunriseHour = 6
    val sunriseMinute = 0
    val sunsetHour = 18
    val sunsetMinute = 0

    // Calculate sunrise and sunset times for the current date
    val sunrise = currentTime.clone() as Calendar
    sunrise.set(Calendar.HOUR_OF_DAY, sunriseHour)
    sunrise.set(Calendar.MINUTE, sunriseMinute)

    val sunset = currentTime.clone() as Calendar
    sunset.set(Calendar.HOUR_OF_DAY, sunsetHour)
    sunset.set(Calendar.MINUTE, sunsetMinute)

    // Check if the current time is after sunrise and before sunset
    return currentTime.after(sunrise) && currentTime.before(sunset)
}