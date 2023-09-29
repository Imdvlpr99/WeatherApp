package com.imdvlpr.weatherappp.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    companion object {
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.bind(inflater.inflate(R.layout.fragment_search, container, false))
        initView()
        return binding.root
    }

    private fun initView() {

    }
}