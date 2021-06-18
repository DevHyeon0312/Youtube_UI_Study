package com.devhyeon.youtubelayout.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devhyeon.youtubelayout.databinding.FragmentFavoriteBinding

class FavoriteFragment: Fragment() {
    companion object {
        private val TAG = FavoriteFragment::class.java.name
    }

    //바인딩
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

}