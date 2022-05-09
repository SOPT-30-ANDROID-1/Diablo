package org.sopt.diablo.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.diablo.data.UserData
import org.sopt.diablo.view.CameraFragment
import org.sopt.diablo.view.HomeFragment
import org.sopt.diablo.view.MainActivity
import org.sopt.diablo.view.ProfileFragment

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