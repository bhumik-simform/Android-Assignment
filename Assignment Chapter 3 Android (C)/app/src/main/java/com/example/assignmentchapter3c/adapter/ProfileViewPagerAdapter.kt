package com.example.assignmentchapter3c.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignmentchapter3c.ui.MyFavoritesFragment
import com.example.assignmentchapter3c.ui.ProfileFragment
import com.example.assignmentchapter3c.ui.RecentFragment

class ProfileViewPagerAdapter(fragment: ProfileFragment) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment = when (position) {
            0 -> RecentFragment()
            1 -> MyFavoritesFragment()
            else -> throw IllegalArgumentException("Invalid position")
    }

    override fun getItemCount() = 2
}
