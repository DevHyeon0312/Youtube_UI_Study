package com.devhyeon.youtubelayout.viewmodels

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhyeon.youtubelayout.R
import com.devhyeon.youtubelayout.constant.FAVORITE_FRAGMENT
import com.devhyeon.youtubelayout.constant.HOME_FRAGMENT
import com.devhyeon.youtubelayout.constant.LOCKER_FRAGMENT
import com.devhyeon.youtubelayout.constant.SEARCH_FRAGMENT
import kotlinx.coroutines.launch

/** BottomNavigation Click Event 를 처리하기 위한 ViewModel */
class BottomNavigationViewModel: ViewModel() {
    //보여줘야 하는 프래그먼트
    private val _selectFragmentTAG = MutableLiveData<Int>().apply { HOME_FRAGMENT }
    val selectFragmentTAG : LiveData<Int> get() = _selectFragmentTAG

    /** 클릭 이벤트에 따라 보여줘야하는 Fragment 선택 */
    fun clickNavigation(@IdRes id:Int) {
        viewModelScope.launch {
            when(id) {
                R.id.home -> {
                    _selectFragmentTAG.value = HOME_FRAGMENT
                }
                R.id.search -> {
                    _selectFragmentTAG.value = SEARCH_FRAGMENT
                }
                R.id.favorite -> {
                    _selectFragmentTAG.value = FAVORITE_FRAGMENT
                }
                R.id.locker -> {
                    _selectFragmentTAG.value = LOCKER_FRAGMENT
                }
            }
        }
    }

    /** navigationData 의 값이 할당되기 전에 호출하는 경우 default(HOME) 값으로 결정 */
    fun initNavigation() {
        if(selectFragmentTAG.value == null) {
            _selectFragmentTAG.value = HOME_FRAGMENT
        }
    }
}