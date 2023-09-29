package com.imdvlpr.weatherappp.fragments.home

import com.imdvlpr.weatherappp.helper.base.BaseView
import com.imdvlpr.weatherappp.model.CurrentForecast
import com.imdvlpr.weatherappp.model.StatusResponse

interface HomeInterface: BaseView {

    fun onSuccessGetForecast(response: CurrentForecast)
    fun onFailed(statusResponse: StatusResponse)
    fun onProgress()
    fun onFinishProgress()

}