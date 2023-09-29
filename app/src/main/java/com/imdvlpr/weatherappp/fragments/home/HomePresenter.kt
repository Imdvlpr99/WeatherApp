package com.imdvlpr.weatherappp.fragments.home

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.imdvlpr.weatherappp.helper.API
import com.imdvlpr.weatherappp.helper.base.BasePresenter
import com.imdvlpr.weatherappp.helper.DispatchGroup

class HomePresenter(private val context: Context): BasePresenter<HomeInterface> {

    var view: HomeInterface? = null
    var api: API? = null
    var dispatchGroup: DispatchGroup? = null

    override fun onAttach(view: HomeInterface) {
        this.view = view
        if (api == null) api = API(context)
        if (dispatchGroup == null) {
            dispatchGroup = DispatchGroup()
            dispatchGroup?.notify { view.onFinishProgress() }
        }
    }

    override fun onDetach() {
        view = null
    }

    fun getForecast(location: String, days: Int) {
        view?.onProgress()
        dispatchGroup?.enter()

        api?.getForecast(location, days) { response, status ->
            val mainHandler = Handler(Looper.getMainLooper())
            mainHandler.postDelayed({
                when(status.isSuccess) {
                    true -> view?.onSuccessGetForecast(response)
                    else -> view?.onFailed(status)
                }
                dispatchGroup?.leave()
            }, 2000)
        }
    }
}