package org.sopt.diablo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.diablo.R
import org.sopt.diablo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var position = FOLLOWER_POSITION
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        val followerFragment = ProfileFollowerFragment()
        val repoFragment = ProfileRepoFragment()
        supportFragmentManager.beginTransaction().add(R.id.container_list, followerFragment).commit()

        binding.apply {
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
        supportFragmentManager.beginTransaction().replace(R.id.container_list, fragment).commit()
        position = pos
    }

    companion object {
        const val FOLLOWER_POSITION = 1
        const val REPO_POSITION = 2
    }
}

