package com.devhyeon.youtubelayout.utils

import android.view.View

/** View Show Util */
fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}