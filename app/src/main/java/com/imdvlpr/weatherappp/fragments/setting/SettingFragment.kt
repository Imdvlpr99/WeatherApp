package com.imdvlpr.weatherappp.fragments.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    companion object {
        fun newInstance(): SettingFragment {
            val fragment = SettingFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingBinding.bind(inflater.inflate(R.layout.fragment_setting, container, false))
        initView()
        return binding.root
    }

    private fun initView() {

    }
}