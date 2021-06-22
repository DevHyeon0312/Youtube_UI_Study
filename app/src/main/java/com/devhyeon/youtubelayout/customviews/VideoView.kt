package com.devhyeon.youtubelayout.customviews

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView

class VideoView(context: Context, attributeSet: AttributeSet? = null) : AppCompatImageView(context, attributeSet) {

    fun setEndPadding(value: Float) {
        setPadding(0, 0, value.toInt(), 0)
    }
}