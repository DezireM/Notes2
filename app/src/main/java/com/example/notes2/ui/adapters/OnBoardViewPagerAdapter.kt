package com.example.notes2.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.notes2.ui.fragments.onboard.OnBoardPagingFragment
import com.example.notes2.ui.fragments.onboard.OnBoardPagingFragment.Companion.ARG_ONBOARD_POSITION

class OnboardViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int) = OnBoardPagingFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ONBOARD_POSITION, position)
        }
    }
}