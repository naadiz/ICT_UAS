package com.zp.st.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zp.st.adapter.HomeAdapter
import com.zp.st.R
import com.zp.st.databinding.ActivityHomeBinding
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var tabAdapter: HomeAdapter
    lateinit var qrEncoder: QRGEncoder
    lateinit var bitmap: Bitmap
    private val tabTitle = listOf(
        R.drawable.tab_one_selector,
        R.drawable.tab_two_selector
//        R.drawable.tab_three_selector,
//        R.drawable.tab_four_selector
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViewPager()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    fun homeToLogin() {
        val k = Intent(this@HomeActivity, LoginActivity::class.java)
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

    fun qrGenerate(){
        val windowManager: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val point: Point = Point()
        val width = point.x
        val height = point.y

        var dimen = if (width < height) width else height
        dimen = dimen * 3 / 4

        qrEncoder = QRGEncoder("vvv", null, QRGContents.Type.TEXT, dimen)

        try {
            bitmap = qrEncoder.getBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setViewPager() {
        binding.vpHome.isUserInputEnabled = false

        val icon = ArrayList(tabTitle)

        binding.apply {
            vpHome.apply {
                tabAdapter = HomeAdapter(this@HomeActivity, this)
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