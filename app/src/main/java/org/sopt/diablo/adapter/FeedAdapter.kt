package org.sopt.diablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.diablo.data.FeedData
import org.sopt.diablo.data.FeedImageData
import org.sopt.diablo.databinding.ItemFeedBinding
import org.sopt.diablo.util.dpToPx
import org.sopt.diablo.view.GridSpaceDecoration


class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    val feedList = mutableListOf<FeedData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding =
            ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(feedList[position])
    }

    override fun getItemCount(): Int = feedList.size

    class FeedViewHolder(
        private val binding: ItemFeedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FeedData) {
            val customDecoration = when (data.images.size) {
                2 -> GridSpaceDecoration(dpToPx(itemView.context, 8f).toInt(), 2)
                else -> GridSpaceDecoration(dpToPx(itemView.context, 11f).toInt(), 3)
            }

            val feedImageAdapter = FeedImageAdapter().apply {
                feedImageList.addAll(data.images)
                notifyDataSetChanged()
            }

            Glide.with(itemView).load(data.profile).circleCrop().into(binding.ivProfile)
            with(binding) {
                tvTitle.text = data.title
                tvDescription.text = data.description
                rvBody.apply {
                    adapter = feedImageAdapter
                    layoutManager = GridLayoutManager(context, data.images.size)
                    addItemDecoration(customDecoration)
                }
            }
        }
    }
}