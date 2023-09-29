package com.imdvlpr.weatherappp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.imdvlpr.weatherappp.databinding.ActivityMainBinding
import com.imdvlpr.weatherappp.fragments.home.HomeFragment
import com.imdvlpr.weatherappp.fragments.search.SearchFragment
import com.imdvlpr.weatherappp.fragments.setting.SettingFragment
import com.imdvlpr.weatherappp.helper.Constants
import com.imdvlpr.weatherappp.helper.SharedPreference
import com.imdvlpr.weatherappp.helper.base.BaseActivity
import com.imdvlpr.weatherappp.helper.getStatusBarHeight
import com.imdvlpr.weatherappp.helper.setWindowFlag
import com.imdvlpr.weatherappp.helper.ui.CustomBottomNavigation
import java.io.IOException
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentHome: HomeFragment
    private lateinit var fragmentSearch: SearchFragment
    private lateinit var fragmentSetting: SettingFragment
    private lateinit var sessionManager: SharedPreference

    private var listFragment: ArrayList<Fragment> = ArrayList()
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SharedPreference()
        sessionManager.sessionManager(this)
        initView()
        checkLocationPermission()
    }

    private fun initView() {
        binding.customNav.setListener(navListener)
        initFragment()
    }

    private fun initFragment() {
        fragmentHome = HomeFragment.newInstance()
        fragmentSearch = SearchFragment.newInstance()
        fragmentSetting = SettingFragment.newInstance()

        listFragment.add(fragmentHome)
        listFragment.add(fragmentSearch)
        listFragment.add(fragmentSetting)

        navListener.onHomeClick()
    }

    private var navListener = object : CustomBottomNavigation.ClickListener {
        override fun onHomeClick() {
            setSelected(0)
        }

        override fun onSearchClick() {
            setSelected(1)
        }

        override fun onSettingClick() {
            setSelected(2)
        }

    }

    fun setSelected(position: Int) {
        when (position) {
            0 -> {
                displayFragment(fragmentHome)
                fragmentHome.refreshPage()
            }
            1 -> displayFragment(fragmentSearch)
            2 -> displayFragment(fragmentSetting)
        }
    }

    private fun displayFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        if (fragment.isAdded) {
            ft.show(fragment)
        } else {
            ft.add(R.id.frameContainer, fragment)
        }
        listFragment.map {
            if (it != fragment && it.isAdded) {
                ft.hide(it)
            }
        }
        ft.commit()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            fetchLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                fetchLocation()
            } else {
                exitProcess(0)
            }
        }
    }

    private fun fetchLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider = LocationManager.NETWORK_PROVIDER

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val lastKnownLocation: Location? = locationManager.getLastKnownLocation(locationProvider)

            if (lastKnownLocation != null) {
                val latitude = lastKnownLocation.latitude
                val longitude = lastKnownLocation.longitude
                val cityName = getCityName(latitude, longitude)
                Log.d("LongLat", "$latitude+$longitude")
                sessionManager.saveToPref(Constants.PREF.LONGITUDE, longitude)
                sessionManager.saveToPref(Constants.PREF.LATITUDE, latitude)
                sessionManager.saveToPref(Constants.PREF.CITY_NAME, cityName)

            } else {
                Log.d("location", "null")
            }
        }
    }

    private fun getCityName(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val address = addresses[0]
                    return address.adminArea ?: ""
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}