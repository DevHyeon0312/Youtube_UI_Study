package com.devhyeon.youtubelayout.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.devhyeon.youtubelayout.adapters.VideoListAdapter
import com.devhyeon.youtubelayout.data.SampleData
import com.devhyeon.youtubelayout.data.VideoListItem
import com.devhyeon.youtubelayout.databinding.FragmentHomeBinding

/** 홈 Fragment */
class HomeFragment: Fragment() {
    companion object {
        private val TAG = HomeFragment::class.java.name
    }

    //바인딩
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val adapter : VideoListAdapter = VideoListAdapter().apply {
        addItems(SampleData)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.videoRecyclerView.adapter = adapter

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}