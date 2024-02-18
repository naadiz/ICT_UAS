package com.zp.st.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zp.st.fragment.FirstForgotFragment
import com.zp.st.fragment.SecondForgotFragment
import com.zp.st.fragment.ThirdForgotFragment

class ForgotAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity){
    private val fragments = listOf(
        FirstForgotFragment.newInstance(FIRST),
        SecondForgotFragment.newInstance(SECOND),
        ThirdForgotFragment.newInstance(LAST)
    )

    companion object{
        const val FIRST = 0
        const val SECOND = 1
        const val LAST = 2
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment = fragments[position]

    fun getRegisteredFragment(position: Int): Fragment = fragments[position]
}