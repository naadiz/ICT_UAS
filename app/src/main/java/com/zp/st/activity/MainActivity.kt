package com.zp.st.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zp.st.R
import com.zp.st.adapter.HomeAdapter
import com.zp.st.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var tabAdapter: HomeAdapter
    private val tabTitle = listOf(
        R.drawable.tab_one_selector,
        R.drawable.tab_two_selector,
        R.drawable.tab_three_selector,
        R.drawable.tab_four_selector
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViewPager()
    }

    fun homeToLogin() {
        val k = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(k)
    }

    fun hideTabLayout() {
        binding.apply {
            tabLayout.visibility = View.GONE
        }
    }

    fun showTabLayout() {
        binding.apply {
            tabLayout.visibility = View.VISIBLE
        }
    }

    private fun setViewPager() {
        val icon = ArrayList(tabTitle)

        binding.apply {
            vpHome.apply {
                tabAdapter = HomeAdapter(this@MainActivity, this)
                adapter = tabAdapter
                currentItem = 0
            }

            vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        HomeAdapter.FIRST -> {
                            binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.tab_one_selector)
                        }
                        HomeAdapter.SECOND -> {
                            binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.tab_two_selector)
                        }
//                        HomeAdapter.THIRD -> {
//                            binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.tab_three_selector)
//                        }
//                        HomeAdapter.LAST -> {
//                            binding.tabLayout.getTabAt(3)!!.setIcon(R.drawable.tab_four_selector)
//                        }
                    }
                }
            })

            TabLayoutMediator(tabLayout, vpHome) { tab, position ->
                tab.setIcon(icon[position])
            }.attach()
        }
    }
}