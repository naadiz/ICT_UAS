package com.zp.st.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.zp.st.adapter.ForgotAdapter
import com.zp.st.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotBinding
    lateinit var tabAdapter: ForgotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityForgotBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViewPager()
    }

    fun setItemOne() {
        binding.apply {
            vpForgot.currentItem = 1
        }
    }

    fun setItemTwo() {
        binding.apply {
            vpForgot.currentItem = 2
        }
    }

    fun setViewPager() {
        binding.apply {
            vpForgot.apply {
                tabAdapter = ForgotAdapter(this@ForgotActivity)
                adapter = tabAdapter
                currentItem = 0
            }

            vpForgot.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        ForgotAdapter.FIRST -> {

                        }
                        ForgotAdapter.SECOND -> {

                        }
                        ForgotAdapter.LAST -> {

                        }
                    }
                }
            })
        }
    }
}