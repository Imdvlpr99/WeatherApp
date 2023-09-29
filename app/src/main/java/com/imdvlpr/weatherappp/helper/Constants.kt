package com.imdvlpr.weatherappp.helper

object Constants {

    interface PREF {
        companion object {
            const val PREF_NAME = "weatherApp"
            const val LONGITUDE = "LONGITUDE"
            const val LATITUDE = "LATITUDE"
            const val TIMEZONE_ID = "TIMEZONE_ID"
            const val CITY_NAME = "CITY_NAME"
        }
    }

    interface API_KEY {
        companion object {
            const val WEATHER_KEY = "d5f50e5cebdd4799a0c81324231909"
        }
    }

    interface PARAM {
        companion object {
            const val CURRENT = "current.json?key="
            const val FORECAST = "forecast.json?key="
            const val SEARCH = "search.json?key="
            const val FUTURE = "future.json?key="
        }
    }

    interface ADDITIONAL_PARAM {
        companion object {
            const val LOCATION = "&q="
            const val AIR_QUALITY = "&aqi="
            const val DAYS = "&days="
            const val ALERTS = "&alerts="
            const val DATETIME = "&dt="
        }
    }

    interface WEATHER_CODE {
        companion object {
            const val SUNNY = 1000
            const val PARTLY_CLOUDY = 1003
            const val CLOUDY = 1006
            const val OVERCAST = 1009
            const val MIST = 1030
            const val PATCHY_RAIN_POSSIBLE = 1063
            const val FOG = 1135
            const val THUNDERY_OUTBREAKS_POSSIBLE = 1087
            const val LIGHT_DRIZZLE = 1168
            const val PATCHY_LIGHT_RAIN = 1180
            const val LIGHT_RAIN = 1183
            const val MODERATE_RAIN_AT_TIMES = 1186
            const val MODERATE_RAIN = 1189
            const val HEAVY_RAIN = 1195
            const val HEAVY_RAIN_WITH_THUNDER = 1276
            const val LIGHT_RAIN_WITH_THUNDER = 1273
            const val LIGHT_RAIN_SHOWER = 1240
            const val HEAVY_RAIN_SHOWER = 1243
        }
    }
}