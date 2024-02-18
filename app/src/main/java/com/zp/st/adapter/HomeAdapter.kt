package com.zp.st.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.zp.st.fragment.HomeFragment
import com.zp.st.fragment.HistoryFragment

class HomeAdapter(fragmentActivity: FragmentActivity, viewPager2: ViewPager2) :
    FragmentStateAdapter(fragmentActivity){
    private val fragments = listOf(
        HomeFragment.newInstance(FIRST),
        HistoryFragment.newInstance(SECOND)
//        BarcodeFragment.newInstance(THIRD),
//        ProfileFragment.newInstance(LAST)
    )

    companion object {
        const val FIRST = 0
        const val SECOND = 1
//        const val THIRD = 2
//        const val LAST = 3
    }

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]

    fun getRegisteredFragment(position: Int): Fragment = fragments[position]
}