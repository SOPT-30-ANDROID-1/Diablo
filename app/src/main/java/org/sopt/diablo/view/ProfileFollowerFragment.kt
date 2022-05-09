package org.sopt.diablo.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.diablo.R
import org.sopt.diablo.adapter.FollowerAdapter
import org.sopt.diablo.data.ServiceCreator
import org.sopt.diablo.data.UserData
import org.sopt.diablo.databinding.FragmentProfileFollowerBinding
import org.sopt.diablo.databinding.FragmentProfileRepoBinding
import org.sopt.diablo.util.enqueueUtil

class ProfileFollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentProfileFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileFollowerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setOnItemClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter()
        val id = arguments?.getString("id").toString()
        val call = ServiceCreator.githubService.getFollowers(id)
        call.enqueueUtil(
            onSuccess = {
                followerAdapter.setItems(it)
            }
        )
        binding.rvFollowerList.adapter = followerAdapter
    }

    private fun setOnItemClickListener() {
        followerAdapter.setItemClickListener { data ->
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("profile", data.avatar_url)
                putExtra("name", data.id)
                putExtra("introduction", data.html_url)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }
}