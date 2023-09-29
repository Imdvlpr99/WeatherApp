package com.imdvlpr.weatherappp.helper

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.imdvlpr.weatherappp.BuildConfig.BASE_URL
import com.imdvlpr.weatherappp.model.CurrentForecast
import com.imdvlpr.weatherappp.model.StatusResponse
import eu.amirs.JSON
import org.json.JSONException
import org.json.JSONObject
import javax.security.auth.callback.Callback

class API(private val context: Context) {

    fun getForecast(location: String, days: Int, callback: (CurrentForecast, StatusResponse) -> Unit) {
        val requestQueue = Volley.newRequestQueue(context)
        val url = BASE_URL +
                Constants.PARAM.FORECAST +
                Constants.API_KEY.WEATHER_KEY +
                Constants.ADDITIONAL_PARAM.LOCATION + location +
                Constants.ADDITIONAL_PARAM.DAYS + days +
                Constants.ADDITIONAL_PARAM.AIR_QUALITY + "yes"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response: String ->
                try {
                    val headers = parseResponseHeaders(response)
                    callback(CurrentForecast(JSON(response)), StatusResponse())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error: VolleyError? ->
            error?.printStackTrace()
        }
        requestQueue.add(stringRequest)
    }

    private fun parseResponseHeaders(response: String): Map<String, String> {
        // Split the response into lines
        val lines = response.split("\n")

        // Create a mutable map to store headers
        val headers = mutableMapOf<String, String>()

        // Parse each line to extract headers
        for (line in lines) {
            val parts = line.split(":")
            if (parts.size >= 2) {
                val key = parts[0].trim()
                val value = parts[1].trim()
                headers[key] = value
            }
        }

        return headers
    }
}