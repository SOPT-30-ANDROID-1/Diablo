package org.sopt.diablo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.diablo.adapter.FollowTabViewPagerAdapter
import org.sopt.diablo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")
    private lateinit var followTabViewPagerAdapter : FollowTabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        val fragmentList = listOf(HomeFollowingFragment(), HomeFollowerFragment())
        followTabViewPagerAdapter = FollowTabViewPagerAdapter(this).apply {
            fragments.addAll(fragmentList)
        }
        binding.vpFollow.adapter = followTabViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tabFollow, binding.vpFollow) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }
}