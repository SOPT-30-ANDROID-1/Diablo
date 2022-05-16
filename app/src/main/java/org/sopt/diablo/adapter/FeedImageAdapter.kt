package org.sopt.diablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.diablo.data.FeedData
import org.sopt.diablo.data.FeedImageData
import org.sopt.diablo.databinding.ItemFeedBinding
import org.sopt.diablo.databinding.ItemFeedImageBinding


class FeedImageAdapter: RecyclerView.Adapter<FeedImageAdapter.FeedImageViewHolder>() {
    var feedImageList = mutableListOf<FeedImageData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedImageViewHolder {
        val binding =
            ItemFeedImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedImageViewHolder, position: Int) {
        holder.onBind(feedImageList[position])
    }

    override fun getItemCount(): Int = feedImageList.size

    class FeedImageViewHolder(
        private val binding: ItemFeedImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FeedImageData) {
            Glide.with(itemView).load(data.url).into(binding.ivImage)
        }
    }
}