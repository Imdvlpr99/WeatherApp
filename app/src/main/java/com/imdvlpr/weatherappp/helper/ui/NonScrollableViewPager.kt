package com.imdvlpr.weatherappp.helper.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NonScrollableViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    // Override onTouchEvent and onInterceptTouchEvent to disable scrolling
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Disable touch events
        return false
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // Disable touch events
        return false
    }
}