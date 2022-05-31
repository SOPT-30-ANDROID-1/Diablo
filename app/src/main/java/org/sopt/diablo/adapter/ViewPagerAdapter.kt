package org.sopt.diablo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.diablo.view.main.camera.CameraFragment
import org.sopt.diablo.view.main.home.HomeFragment
import org.sopt.diablo.view.main.MainActivity
import org.sopt.diablo.view.main.profile.ProfileFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MainActivity.PROFILE_POSITION -> ProfileFragment()
            MainActivity.HOME_POSITION -> HomeFragment()
            else -> CameraFragment()
        }
    }
}