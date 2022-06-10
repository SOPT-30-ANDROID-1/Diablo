package org.sopt.diablo.view.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.sopt.diablo.R
import org.sopt.diablo.data.ServiceCreator
import org.sopt.diablo.databinding.FragmentProfileBinding
import org.sopt.diablo.util.MyApplication
import org.sopt.diablo.util.enqueueUtil
import org.sopt.diablo.view.HomeActivity
import org.sopt.diablo.view.onboarding.OnboardingActivity

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
        initView()
        initTransactionEvent()
        setOnSettingClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        val name = MyApplication.prefs.getName()
        val id = MyApplication.prefs.getId()
        with(binding) {
            tvName.text = name
            tvId.text = id
        }
        with(ServiceCreator.githubService.getProfile(id)) {
            enqueueUtil(
                onSuccess = {
                    Glide.with(this@ProfileFragment).load(it.avatar_url).into(binding.ivProfile)
                    binding.tvIntroduction.text = it.bio
                }
            )
        }
    }

    private fun initTransactionEvent() {
        val followerFragment = ProfileFollowerFragment()
        val repoFragment = ProfileRepoFragment()
        childFragmentManager.beginTransaction().add(R.id.container_list, followerFragment).commit()

        with(binding) {
            btnFollower.isSelected = true
            btnRepo.isSelected = false
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

    private fun setOnSettingClickListener() {
        binding.ivSetting.setOnClickListener {
            Intent(requireContext(), SettingActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}