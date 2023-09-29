package com.imdvlpr.weatherappp.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.imdvlpr.weatherappp.MainActivity
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.FragmentHomeBinding
import com.imdvlpr.weatherappp.helper.Constants
import com.imdvlpr.weatherappp.helper.SharedPreference
import com.imdvlpr.weatherappp.helper.convertDateToHour
import com.imdvlpr.weatherappp.helper.getCurrentHour
import com.imdvlpr.weatherappp.helper.getStatusBarHeight
import com.imdvlpr.weatherappp.model.CurrentForecast
import com.imdvlpr.weatherappp.model.Hour
import com.imdvlpr.weatherappp.model.StatusResponse
import java.util.Calendar
import java.util.TimeZone

class HomeFragment : Fragment(), HomeInterface {

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: HomePresenter
    private lateinit var sessionManager: SharedPreference
    private lateinit var forecastAdapter: ForecastHourAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        sessionManager = SharedPreference()
        sessionManager.sessionManager(requireContext())
        onAttach()
        initView()

        return binding.root
    }

    private fun initView() {
        binding.customToolbar.apply {
            setPadding(0, requireContext().getStatusBarHeight(), 0, 0)
            setBackIcon(false)
            setInfoIcon(false)
            setTitle(sessionManager.getStringFromPref(Constants.PREF.CITY_NAME), R.color.white)
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            presenter.getForecast(sessionManager.getStringFromPref(Constants.PREF.CITY_NAME), 1)
        }

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.forecastRecyler.layoutManager = layoutManager

        when (getCurrentHour().toInt()) {
            0, 1, 2, 3, 4, 5,
            18, 19, 20, 21, 22, 23-> binding.background.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_night))
            6, 7, 8, 9, 10, 11, 12, 13, 14 -> binding.background.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_morning))
            15, 16, 17 -> binding.background.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_evening))

        }
    }

    override fun onSuccessGetForecast(response: CurrentForecast) {
        val hourItem: ArrayList<Hour> = ArrayList()
        var position = -1
        sessionManager.saveToPref(Constants.PREF.TIMEZONE_ID, response.tzId)
        binding.currentWeather.setWeatherData(response)
        binding.weatherInfo.setWeatherInfo(response)
        response.forecastDay.map {
            for (item in it.hour) {
                hourItem.add(Hour(item.time, item.tempC, item.conditionName, item.conditionIcon, item.conditionCode))
            }
        }
        for ((index, hour) in hourItem.withIndex()) {
            if (convertDateToHour(hour.time) == getCurrentHour()) {
                position = index
                break
            }
        }
        layoutManager.scrollToPosition(position)
        forecastAdapter = ForecastHourAdapter(requireContext(), hourItem)
        binding.forecastRecyler.adapter = forecastAdapter
    }

    override fun onFailed(statusResponse: StatusResponse) {
    }

    override fun onProgress() {
        if (isAdded) {
            (activity as MainActivity).showProgress()
            binding.root.isVisible = false
        }
    }

    override fun onFinishProgress() {
        if (isAdded) {
            (activity as MainActivity).hideProgress()
            binding.root.isVisible = true
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onAttach() {
        presenter = HomePresenter(context as Context)
        presenter.onAttach(this)
        presenter.getForecast(sessionManager.getStringFromPref(Constants.PREF.CITY_NAME), 1)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun refreshPage() {
        if (isAdded) presenter.getForecast(sessionManager.getStringFromPref(Constants.PREF.CITY_NAME), 1)
    }
}