package com.devhyeon.youtubelayout.ui.fragments

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.devhyeon.youtubelayout.data.VideoListItem
import com.devhyeon.youtubelayout.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    //바인딩
    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    var onShow: () -> Unit = {}
    var onHide: () -> Unit = {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageClear.setOnClickListener {
            onHide()
        }

        binding.imagePlay.setOnClickListener {
            onShow()
        }

        binding.videoMotionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                Log.d("onTransitionCompleted ", "VideoFragment")
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }
        })
    }

    fun setItem(video: VideoListItem) {
        Glide.with(binding.videoView.context)
            .load(video.ThumbNail)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade(100))
            .into(binding.videoView)
    }

    fun showFullScreen() {
        binding.videoMotionLayout.transitionToEnd()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}