package com.imdvlpr.weatherappp.activity.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.ActivityForecastBinding
import com.imdvlpr.weatherappp.helper.base.BaseActivity

class ForecastActivity : BaseActivity() {

    private lateinit var binding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}