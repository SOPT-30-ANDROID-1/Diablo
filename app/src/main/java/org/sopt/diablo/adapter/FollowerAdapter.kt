package org.sopt.diablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.diablo.data.UserData
import org.sopt.diablo.databinding.ItemFollowerListBinding

class FollowerAdapter: RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val userList = mutableListOf<UserData>()
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class FollowerViewHolder(
        private val binding: ItemFollowerListBinding,
        private val itemClickListener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.apply {
                ivProfile.setImageResource(data.profile)
                tvName.text = data.name
                tvIntroduction.text = data.introduction
            }
            itemView.setOnClickListener {
                itemClickListener.onClick(data)
            }
        }
    }

    interface ItemClickListener {
        fun onClick(data: UserData)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}