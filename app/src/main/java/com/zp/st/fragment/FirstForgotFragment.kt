package com.zp.st.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zp.st.activity.ForgotActivity
import com.zp.st.databinding.FragmentFirstForgotBinding

class FirstForgotFragment : Fragment(), View.OnClickListener {
    lateinit var binding : FragmentFirstForgotBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstForgotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSubmitForgot.setOnClickListener {
                (activity as ForgotActivity).setItemOne()
            }
        }
    }

    companion object {
        fun newInstance(page: Int) = FirstForgotFragment()
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}