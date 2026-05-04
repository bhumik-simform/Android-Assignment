package com.example.assignmentchapter3c.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.assignmentchapter3c.R
import com.example.assignmentchapter3c.adapter.ProfileViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myTabLayout = view.findViewById<TabLayout>(R.id.profile_tab_layout)

        val myViewPager = view.findViewById<ViewPager2>(R.id.my_view_pager2)

        myViewPager.adapter = ProfileViewPagerAdapter(this)

        TabLayoutMediator(myTabLayout,myViewPager) { tab, position ->
            tab.text = when(position) {
                0-> "Recent"
                1-> "My Favorites"
                else -> ""
            }
        }.attach()

    }
}