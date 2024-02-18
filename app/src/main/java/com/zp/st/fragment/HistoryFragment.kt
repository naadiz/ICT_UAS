package com.zp.st.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zp.st.activity.HomeActivity
import com.zp.st.adapter.HistoryAdapter
import com.zp.st.history.ApiClientHistory
import com.zp.st.history.ResponseModelHistoryItem
import kotlinx.android.synthetic.main.fragment_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.zp.st.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    private lateinit var profile: SharedPreferences
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile = requireActivity().getSharedPreferences(
            ProfileFragment.LOGIN_SESSION,
            Context.MODE_PRIVATE
        )

        adapter = HistoryAdapter(requireContext(), arrayListOf())
        binding.rvHistory.adapter = adapter
        binding.rvHistory.setHasFixedSize(true)

        remoteGetHistory()
        scrollHideAndShowTabLayout()
        setRefresh()
    }

    private fun setRefresh() {
        binding.constraintLayout.setOnRefreshListener {
            remoteGetHistory()
            Toast.makeText(activity, "Page refreshed!", Toast.LENGTH_SHORT).show()
            constraintLayout.isRefreshing = false
        }
    }

    fun remoteGetHistory(){
        ApiClientHistory.apiServiceHistory
            .getRiwayat(profile.getString("id_user", null)!!.toInt())
            .enqueue(object : Callback<ArrayList<ResponseModelHistoryItem>>{
                override fun onResponse(
                    call: Call<ArrayList<ResponseModelHistoryItem>>,
                    response: Response<ArrayList<ResponseModelHistoryItem>>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        if (data != null){
                            setHistoryToAdapter(data!!)
                            binding.apply {
                                tvNoData.visibility = View.GONE
                            }
                        } else {
                            binding.apply {
                                tvNoData.visibility = View.VISIBLE
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<ResponseModelHistoryItem>>,
                    t: Throwable
                ) {
                    Log.d("Error", "" + t.stackTraceToString())
                }

            })
    }

    fun setHistoryToAdapter(data: ArrayList<ResponseModelHistoryItem>){
        adapter.setData(data)
    }

    private fun scrollHideAndShowTabLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.rvHistory.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val x = scrollY - oldScrollY
                if (x < 0) {
                    (activity as HomeActivity?)?.showTabLayout()
                } else if (x > 0) {
                    (activity as HomeActivity?)?.hideTabLayout()
                }
            }
        }
    }

    companion object {
        fun newInstance(page: Int) = HistoryFragment()
    }
}