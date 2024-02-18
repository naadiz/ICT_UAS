package com.zp.st

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zp.st.databinding.ActivityEducatorBinding

class EducatorActivity : AppCompatActivity() {
    lateinit var binding: ActivityEducatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEducatorBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}