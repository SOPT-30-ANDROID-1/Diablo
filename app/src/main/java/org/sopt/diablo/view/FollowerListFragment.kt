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
import org.sopt.diablo.databinding.FragmentFollowerListBinding
import org.sopt.diablo.databinding.ItemFollowerListBinding

class FollowerListFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerListBinding? = null
    private val binding get() = _binding?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        listClickEvent()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        val customDecoration = LinearSpaceDecoration(50)
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
        binding.rvFollowerList.apply {
            addItemDecoration(customDecoration)
            adapter = followerAdapter
        }
    }

    private fun listClickEvent() {
        followerAdapter.setItemClickListener(object : FollowerAdapter.ItemClickListener {
            override fun onClick(data: UserData) {
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("profile", data.profile)
                    putExtra("name", data.name)
                    putExtra("introduction", data.introduction)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        })
    }
}