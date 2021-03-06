package com.devhyeon.youtubelayout.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.devhyeon.youtubelayout.R
import com.devhyeon.youtubelayout.constant.FAVORITE_FRAGMENT
import com.devhyeon.youtubelayout.constant.HOME_FRAGMENT
import com.devhyeon.youtubelayout.constant.LOCKER_FRAGMENT
import com.devhyeon.youtubelayout.constant.SEARCH_FRAGMENT
import com.devhyeon.youtubelayout.customviews.BasicBottomSheet
import com.devhyeon.youtubelayout.data.VideoListItem
import com.devhyeon.youtubelayout.databinding.ActivityMainBinding
import com.devhyeon.youtubelayout.ui.fragments.*
import com.devhyeon.youtubelayout.viewmodels.BottomNavigationViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

/** 메인 Activity */
class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.name
    }

    //ViewModel
    private val bottomNavigationVM: BottomNavigationViewModel by viewModel()

    //바인딩
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //Bottom Navigation Select Item 에 따라 화면에 보여줄 Fragment
    private val homeFragment        by lazy { HomeFragment() }
    private val searchFragment      by lazy { SearchFragment() }
    private val favoriteFragment    by lazy { FavoriteFragment() }
    private val lockerFragment      by lazy { LockerFragment() }
    private val videoFragment       by lazy { VideoFragment() }
    private val bottomDrawerFragment by lazy { BottomDrawerFragment() }
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment()

        bottomNavigationVM.initNavigation()
    }

    override fun onStart() {
        super.onStart()
        /** Navigation Select Event 를 ViewModel 로 전달 */
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            bottomNavigationVM.clickNavigation(it.itemId)
            true
        }

        /** Observer */
        with(bottomNavigationVM) {
            selectFragmentTAG.observe(this@MainActivity, Observer {
                it?.let { fragmentId -> selectFragment(fragmentId) }
                binding.appBar.setExpanded(true)
            })
        }

        homeFragment.adapter.onClick = {
            show(it)
        }
        videoFragment.onHide = {
            hide()
        }
        videoFragment.onShow = {
            show()
        }

        val callback = (object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        binding.container.visibility = View.VISIBLE
                        binding.bottomBack.visibility = View.GONE
                        binding.fab.isEnabled=true
                    }
                    else -> {}
                }
            }
        })

        val bottomSheet = BasicBottomSheet
            .Begin(this@MainActivity)
            .hide(true)
            .addCallBack(callback)
            .addContents(bottomDrawerFragment)
            .hidden(true)
            .commit()

        binding.bottomBack.setOnClickListener {
            binding.container.visibility = View.VISIBLE
            binding.bottomBack.visibility = View.GONE
            bottomSheet.hidden(true)
        }

        binding.fab.setOnClickListener {
            binding.container.visibility = View.GONE
            binding.fab.isEnabled=false
            binding.bottomBack.visibility = View.VISIBLE
            bottomSheet.hidden(false)
        }
    }

    /** 프래그먼트 결정 */
    private fun selectFragment(fragmentId: Int) {
        when(fragmentId) {
            HOME_FRAGMENT -> {
                changeFragment(homeFragment)
            }
            SEARCH_FRAGMENT -> {
                changeFragment(searchFragment)
            }
            FAVORITE_FRAGMENT -> {
                changeFragment(favoriteFragment)
            }
            LOCKER_FRAGMENT -> {
                changeFragment(lockerFragment)
            }
        }
    }

    /** 프래그먼트 등록 */
    private fun addFragment() {
        fragmentManager.beginTransaction().add(R.id.fl_container, homeFragment).hide(videoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.fl_container, searchFragment).hide(videoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.fl_container, favoriteFragment).hide(videoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.fl_container, lockerFragment).hide(videoFragment).commit()
        fragmentManager.beginTransaction().add(R.id.container,videoFragment).hide(videoFragment).commit()
    }

    /** 프래그먼트 교체 */
    private fun changeFragment(fragment: Fragment) {
        println(fragment)
        fragmentManager.fragments.forEach {
            if(it != fragment && it != videoFragment && it != bottomDrawerFragment) {
                fragmentManager.beginTransaction().hide(it).commit()
            }
        }

        fragmentManager.beginTransaction().show(fragment).commit()
    }

    private fun show(video: VideoListItem) {
        fragmentManager.beginTransaction().show(videoFragment).commit()
        videoFragment.setItem(video)
        videoFragment.showFullScreen()
    }

    private fun show() {
        fragmentManager.beginTransaction().show(videoFragment).commit()
        videoFragment.showFullScreen()
    }

    private fun hide() {
        fragmentManager.beginTransaction().hide(videoFragment).commit()
    }
}