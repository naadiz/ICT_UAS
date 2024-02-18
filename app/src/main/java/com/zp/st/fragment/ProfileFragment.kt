package com.zp.st.fragment

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zp.st.ApiClientFetch
import com.zp.st.ResponseModel
import com.zp.st.activity.HomeActivity
import com.zp.st.activity.LoginActivity
import com.zp.st.adapter.AnakAdapter
import com.zp.st.databinding.FragmentProfileBinding
import com.zp.st.fetchUserData.UserDataPresenter
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private lateinit var profile: SharedPreferences
    lateinit var adapter: AnakAdapter
    lateinit var presenter: UserDataPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile = requireActivity().getSharedPreferences(LOGIN_SESSION, MODE_PRIVATE)

        binding.apply {
            tvNamaAkun.text = profile
                .getString("nama_depan", null) + " " + profile
                .getString("nama_belakang", null)
            tvJenisKelamin.text = profile.getString("jenis_kelamin", null)
            tvUsername.text = profile.getString("username", null)
            tvEmail.text = profile.getString("email", null)
            tvNoHp.text = profile.getString("no_handphone", null)
            tvUsiaOrtu.text = profile.getString("usia_user", null) + " Tahun"
            tvTinggiOrtu.text = profile.getString("tinggi_user", null) + " cm"
            tvBeratOrtu.text = profile.getString("berat_user", null) + " kg"
            tvRiwayaOrtu.text = "Riwayat penyakit: " + profile.getString("riwayat_user", null)

            btnLogout.setOnClickListener {
                profile.edit().clear().commit()
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
        adapter = AnakAdapter(requireContext(), arrayListOf())
        binding.rvDataAnak.adapter = adapter
        binding.rvDataAnak.setHasFixedSize(true)

        remoteGetUsersData()
        scrollHideAndShowTabLayout()
        setRefresh()
    }

    private fun setRefresh() {
        binding.consLayPro.setOnRefreshListener {
            remoteGetUsersData()
            Toast.makeText(activity, "Page refreshed!", Toast.LENGTH_SHORT).show()
            cons_lay_pro.isRefreshing = false
        }
    }

    fun remoteGetUsersData(){
        ApiClientFetch.apiFetchService
            .getUsers(profile.getString("username", null)!!, profile.getString("password", null)!!)
            .enqueue(object : Callback<ArrayList<ResponseModel>>{
                override fun onResponse(
                    call: Call<ArrayList<ResponseModel>>,
                    response: Response<ArrayList<ResponseModel>>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        if (data != null){
                            setDataToAdapter(data!!)
                        } else {
                            // No Action
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<ResponseModel>>, t: Throwable) {
                    Log.d("Error", "" + t.stackTraceToString())
                }

            })
    }

    fun setDataToAdapter(data: ArrayList<ResponseModel>){
        adapter.setData(data)
    }

    companion object {
        fun newInstance(page: Int) = ProfileFragment()
        const val LOGIN_SESSION = "login_session"
    }

    private fun scrollHideAndShowTabLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.consLayPro.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val x = scrollY - oldScrollY
                if (x < 0) {
                    (activity as HomeActivity?)?.showTabLayout()
                } else if (x > 0) {
                    (activity as HomeActivity?)?.hideTabLayout()
                }
            }
        }
    }
}