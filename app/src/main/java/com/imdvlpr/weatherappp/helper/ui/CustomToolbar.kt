package com.imdvlpr.weatherappp.helper.ui

import android.content.Context
import android.icu.text.CaseMap.Title
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.imdvlpr.weatherappp.R
import com.imdvlpr.weatherappp.databinding.LayoutCustomToolbarBinding

class CustomToolbar: ConstraintLayout {

    private lateinit var binding: LayoutCustomToolbarBinding
    private var listener: ToolbarClickListener? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        binding = LayoutCustomToolbarBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_custom_toolbar, this, true))

        binding.backBtn.setOnClickListener {
            listener?.onBackClick()
        }

        binding.infoBtn.setOnClickListener {
            listener?.onMiscClick()
        }
    }

    fun setTitle(title: String, textColor: Int) {
        binding.titleName.text = title
        binding.titleName.setTextColor(ContextCompat.getColor(context, textColor))
    }

    fun setBackIcon(isVisible: Boolean = false) {
        binding.backBtn.isVisible = isVisible
    }

    fun setInfoIcon(isVisible: Boolean = false) {
        binding.infoBtn.isVisible = isVisible
    }

    fun setListener(listener: ToolbarClickListener) {
        this.listener = listener
    }

    interface ToolbarClickListener {
        fun onBackClick()
        fun onMiscClick()
    }
}