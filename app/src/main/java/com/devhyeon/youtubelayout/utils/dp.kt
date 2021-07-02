package com.devhyeon.youtubelayout.utils

import android.content.res.Resources

/** Int Value -> dp */
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

/** Float Value -> dp */
val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
