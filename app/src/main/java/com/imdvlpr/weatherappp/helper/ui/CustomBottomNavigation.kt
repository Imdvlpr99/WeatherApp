package com.imdvlpr.weatherappp.helper.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.CustomBottomNavBinding

class CustomBottomNavigation: ConstraintLayout {

    private lateinit var binding: CustomBottomNavBinding
    private var listener: ClickListener? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        binding = CustomBottomNavBinding.bind(LayoutInflater.from(context).inflate(R.layout.custom_bottom_nav, this, true))

        binding.menuHome.setOnClickListener {
            setSelected(0)
            listener?.onHomeClick()
        }

        binding.menuSearch.setOnClickListener {
            setSelected(1)
            listener?.onSearchClick()
        }

        binding.menuSetting.setOnClickListener {
            setSelected(2)
            listener?.onSettingClick()
        }

        binding.menuHome.performClick()
    }

    private fun setSelected(id: Int) {
        when(id) {
            0 -> {
                binding.menuHome.background = ContextCompat.getDrawable(context, R.drawable.layout_bg_rounded)
                binding.menuSearch.background = null
                binding.menuSetting.background = null
                binding.iconHome?.setColorFilter(ContextCompat.getColor(context, R.color.pinkSoft))
                binding.iconSearch?.setColorFilter(ContextCompat.getColor(context, R.color.blueSoft))
                binding.iconSetting?.setColorFilter(ContextCompat.getColor(context, R.color.blueSoft))
            }
            1 -> {
                binding.menuHome.background = null
                binding.menuSearch.background = ContextCompat.getDrawable(context, R.drawable.layout_bg_rounded)
                binding.menuSetting.background = null
                binding.iconHome?.setColorFilter(ContextCompat.getColor(context, R.color.blueSoft))
                binding.iconSearch?.setColorFilter(ContextCompat.getColor(context, R.color.pinkSoft))
                binding.iconSetting?.setColorFilter(ContextCompat.getColor(context, R.color.blueSoft))
            }
            2 -> {
                binding.menuHome.background = null
                binding.menuSearch.background = null
                binding.menuSetting.background = ContextCompat.getDrawable(context, R.drawable.layout_bg_rounded)
                binding.iconHome?.setColorFilter(ContextCompat.getColor(context, R.color.blueSoft))
                binding.iconSearch?.setColorFilter(ContextCompat.getColor(context, R.color.blueSoft))
                binding.iconSetting?.setColorFilter(ContextCompat.getColor(context, R.color.pinkSoft))
            }
        }
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    interface ClickListener {
        fun onHomeClick()
        fun onSearchClick()
        fun onSettingClick()
    }
}