package org.sopt.diablo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.diablo.R
import org.sopt.diablo.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {
    private var position = HomeActivity.FOLLOWER_POSITION
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화 되지 않았습니다")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransactionEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTransactionEvent() {
        val followerFragment = ProfileFollowerFragment()
        val repoFragment = ProfileRepoFragment()
        childFragmentManager.beginTransaction().add(R.id.container_list, followerFragment).commit()
        binding.btnFollower.isSelected = true
        binding.btnRepo.isSelected = false

        with(binding) {
            btnFollower.setOnClickListener {
                if (position == REPO_POSITION) {
                    fragmentManage(followerFragment, FOLLOWER_POSITION)
                }
            }
            btnRepo.setOnClickListener{
                if (position == FOLLOWER_POSITION) {
                    fragmentManage(repoFragment, REPO_POSITION)
                }
            }
        }
    }

    private fun fragmentManage(fragment: Fragment, pos: Int) {
        childFragmentManager.beginTransaction().replace(R.id.container_list, fragment).commit()
        with(binding) {
            btnFollower.isSelected = !btnFollower.isSelected
            btnRepo.isSelected = !btnRepo.isSelected
        }
        position = pos
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}