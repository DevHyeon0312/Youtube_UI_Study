package com.devhyeon.youtubelayout.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devhyeon.youtubelayout.databinding.FragmentBottomdrawerBinding
import com.devhyeon.youtubelayout.databinding.FragmentHomeBinding

class BottomDrawerFragment: Fragment() {
    private var _binding: FragmentBottomdrawerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomdrawerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}