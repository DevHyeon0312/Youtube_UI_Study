package com.devhyeon.youtubelayout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.devhyeon.youtubelayout.R
import com.devhyeon.youtubelayout.data.VideoListItem
import com.devhyeon.youtubelayout.databinding.ListVideoItemBinding

class VideoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var videoList: MutableList<VideoListItem> = mutableListOf()
    var onClick: (VideoListItem) -> Unit = {}

    fun addItems(items: List<VideoListItem>) {
        videoList.clear()
        videoList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.list_video_item, parent, false
        )
        return VideoListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VideoListViewHolder).onBind(videoList[position])
    }

    override fun getItemCount(): Int = if (videoList.isNullOrEmpty()) 0 else videoList.size

    private inner class VideoListViewHolder(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        fun onBind(video: VideoListItem) {
            //dataBinding
            (viewDataBinding as ListVideoItemBinding).videoData = video

            Glide.with(itemView.context)
                .load(video.ThumbNail)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .into(viewDataBinding.imageView)

            viewDataBinding.container.setOnClickListener{onClick(video)}
        }
    }
}