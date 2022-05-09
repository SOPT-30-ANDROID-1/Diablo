package org.sopt.diablo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.diablo.data.response.ResponseFollower
import org.sopt.diablo.data.response.ResponseRepo
import org.sopt.diablo.databinding.ItemProfileRepoBinding

class RepoAdapter: RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    val repoList = mutableListOf<ResponseRepo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemProfileRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    fun setItems(newItems: List<ResponseRepo>) {
        repoList.clear()
        repoList.addAll(newItems)
        notifyDataSetChanged()
    }

    class RepoViewHolder(
        private val binding: ItemProfileRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseRepo) {
            binding.apply {
                tvName.text = data.name
                tvIntroduction.text = data.description
            }
        }
    }
}