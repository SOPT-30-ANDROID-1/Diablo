package org.sopt.diablo.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.diablo.R
import org.sopt.diablo.adapter.FollowerAdapter
import org.sopt.diablo.data.UserData
import org.sopt.diablo.databinding.FragmentProfileFollowerBinding
import org.sopt.diablo.databinding.FragmentProfileRepoBinding

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
        followerAdapter = FollowerAdapter().apply {
            userList.addAll(
                listOf(
                    UserData(R.drawable.ic_launcher_background, "정설희", "안드로이드 파트원"),
                    UserData(R.drawable.ic_launcher_background, "정설희2", "안드로이드 파트원2"),
                    UserData(R.drawable.ic_launcher_background, "정설희3", "안드로이드 파트원3"),
                    UserData(R.drawable.ic_launcher_background, "정설희4", "안드로이드 파트원4"),
                    UserData(R.drawable.ic_launcher_background, "정설희5", "안드로이드 파트원5"),
                    UserData(R.drawable.ic_launcher_background, "정설희6", "안드로이드 파트원6"),
                    UserData(R.drawable.ic_launcher_background, "정설희7", "안드로이드 파트원7"),
                    UserData(R.drawable.ic_launcher_background, "정설희8", "안드로이드 파트원8"),
                )
            )
            notifyDataSetChanged()
        }
        binding.rvFollowerList.adapter = followerAdapter
    }

    private fun setOnItemClickListener() {
        followerAdapter.setItemClickListener { data ->
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("profile", data.profile)
                putExtra("name", data.name)
                putExtra("introduction", data.introduction)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }
}