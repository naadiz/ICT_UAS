package com.zp.st.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zp.st.activity.ForgotActivity
import com.zp.st.databinding.FragmentSecondForgotBinding

class SecondForgotFragment : Fragment() {
    lateinit var binding : FragmentSecondForgotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondForgotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSubmitOtp.setOnClickListener{
                (activity as ForgotActivity).setItemTwo()
            }
        }
    }

    companion object {
        fun newInstance(page: Int) = SecondForgotFragment()
    }
}