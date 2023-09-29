package com.imdvlpr.weatherappp.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.imdvlpr.weatherappp.fragments.home.HomeFragment
import com.imdvlpr.weatherappp.fragments.search.SearchFragment
import com.imdvlpr.weatherappp.fragments.setting.SettingFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> SettingFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 3 // Number of tabs
    }
}