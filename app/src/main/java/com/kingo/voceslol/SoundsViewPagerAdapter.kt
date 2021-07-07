package com.kingo.voceslol

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SoundsViewPagerAdapter(fa: FragmentActivity, private val links: Array<SoundsData>): FragmentStateAdapter(fa) {

    companion object {
        const val ARG_LINKS = "links"
    }

    override fun getItemCount(): Int  = links.size
    override fun createFragment(position: Int): Fragment {
        val fragment = SoundsTabFragment()
        fragment.arguments = Bundle().apply {
            putStringArray(ARG_LINKS, links[position].links)
        }
        return fragment
    }

}