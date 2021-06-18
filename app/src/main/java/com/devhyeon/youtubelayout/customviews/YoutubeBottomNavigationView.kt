package com.devhyeon.youtubelayout.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView

/** Custom Navigation View
 * 총 5개의 Item 중 (0,1,2,3,4) 중앙에 해당하는 아이템을 비활성화 하기 위함.
 * */
class YoutubeBottomNavigationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    init {
        val menuView = getChildAt(0) as ViewGroup
        //index 2 : 비활성화 아이템
        menuView.getChildAt(2).isClickable = false
    }
}