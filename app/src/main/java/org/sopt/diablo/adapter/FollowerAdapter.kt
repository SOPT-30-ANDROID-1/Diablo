package org.sopt.diablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.diablo.data.response.ResponseFollower
import org.sopt.diablo.databinding.ItemProfileFollowerBinding

class FollowerAdapter: RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val userList = mutableListOf<ResponseFollower>()
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemProfileFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun setItems(newItems: List<ResponseFollower>) {
        userList.clear()
        userList.addAll(newItems)
        notifyDataSetChanged()
    }

    class FollowerViewHolder(
        private val binding: ItemProfileFollowerBinding,
        private val itemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseFollower) {
            binding.apply {
                Glide.with(itemView).load(data.avatar_url).into(binding.ivProfile)
                tvName.text = data.id
                tvIntroduction.text = data.html_url
            }
            itemView.setOnClickListener {
                itemClickListener.onClick(data)
            }
        }
    }

    fun interface ItemClickListener {
        fun onClick(data: ResponseFollower)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}