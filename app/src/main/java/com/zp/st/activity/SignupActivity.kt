package com.zp.st.activity

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.zp.st.api.model.city.ResponseKota
import com.zp.st.api.model.district.ResponseKec
import com.zp.st.api.model.province.ResponseProvinsi
import com.zp.st.api.model.subdistrict.ResponseKel
import com.zp.st.api.service.RetrofitClient
import com.zp.st.databinding.ActivitySignupBinding
import com.zp.st.loginApi.signup.ResponseModeSignup
import com.zp.st.loginApi.signup.SignupClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivitySignupBinding

    private var listIdProv      = ArrayList<Int>()
    private var listIdKota      = ArrayList<Int>()
    private var listIdKec       = ArrayList<Int>()
    private var listIdKel       = ArrayList<Int>()
    private var listNameProv    = ArrayList<String>()
    private var listNameKota    = ArrayList<String>()
    private var listNameKec     = ArrayList<String>()
    private var listNameKel     = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        showProvince()
        showAndHideCopyright()
        setUpAction()
    }

    private fun setUpAction() {
        binding.apply {
            btnSignup.setOnClickListener {
                SignupClient.apiSignup.toSignUp(
                    1,
                    etUsername.text.toString(),
                    etEmail.text.toString(),
                    etNohp.text.toString(),
                    etPassword.text.toString(),
                    etFirstname.text.toString(),
                    etLastname.text.toString(),
                    etJk.text.toString(),
                    spProvinsi.selectedItem.toString(),
                    spKota.selectedItem.toString(),
                    spKec.selectedItem.toString(),
                    spKel.selectedItem.toString(),
                    etRw.text.toString(),
                    etRt.text.toString(),
                    etJalan.text.toString(),
                    etPostalCode.text.toString(),
                    1,
                    etUmur.text.toString().toInt(),
                    etTinggiOrtu.text.toString().toInt(),
                    etBeratOrtu.text.toString().toInt(),
                    etRiwayatPenyakitOrtu.text.toString(),
                    etNik.text.toString()
                ).enqueue(object : Callback<ResponseModeSignup>{
                    override fun onResponse(
                        call: Call<ResponseModeSignup>,
                        response: Response<ResponseModeSignup>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(this@SignupActivity,
                                "Berhasil terdaftar",
                                Toast.LENGTH_SHORT)
                                .show()
                            val i = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(i)
                        }
                    }

                    override fun onFailure(call: Call<ResponseModeSignup>, t: Throwable) {
                        Toast.makeText(
                            this@SignupActivity,
                            "Terjadi kesalahan",
                            Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            }

            haveAcc.setOnClickListener {
                val j = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(j)
            }
        }
    }

    private fun showAndHideCopyright() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.svSignup.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    val x = scrollY - oldScrollY
                    if (x < 0) {
                        tvCopyright.visibility = View.GONE
                    } else if (x > 0) {
                        tvCopyright.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun showProvince() {
        RetrofitClient.instance.getProvinsi().enqueue(object : Callback<ResponseProvinsi> {
            override fun onResponse(
                call: Call<ResponseProvinsi>,
                response: Response<ResponseProvinsi>
            ) {
                val listResponse = response.body()?.provinsi
                listResponse?.forEach {
                    listIdProv.add(it.id)
                    listNameProv.add(it.nama)
                }

                binding.apply {
                    spProvinsi.onItemSelectedListener = this@SignupActivity
                    val adapter  = ArrayAdapter(this@SignupActivity, R.layout.simple_spinner_dropdown_item, listNameProv)
                    spProvinsi.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseProvinsi>, t: Throwable) {
                Toast.makeText(this@SignupActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showKota(idProv: Int) {
        Log.d("Cek", idProv.toString())
        RetrofitClient.instance.getKota(idProv).enqueue(object : Callback<ResponseKota> {
            override fun onResponse(call: Call<ResponseKota>, response: Response<ResponseKota>) {
                val listResponse = response.body()?.kotaKabupaten

                listIdKota.clear()
                listNameKota.clear()

                listResponse?.forEach {
                    listIdKota.add(it.id)
                    listNameKota.add(it.nama)
                }

                binding.apply {
                    spKota.onItemSelectedListener = this@SignupActivity
                    val adapter = ArrayAdapter(this@SignupActivity, R.layout.simple_spinner_dropdown_item, listNameKota)
                    spKota.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseKota>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
        binding.apply {
            if (parent?.selectedItem == spProvinsi.selectedItem){
                showKota(listIdProv[position])
            } else if (parent?.selectedItem == spKota.selectedItem){
                showKec(listIdKota[position])
            } else if (parent?.selectedItem == spKec.selectedItem){
                showKel(listIdKec[position])
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun showKec(idKota: Int) {
        RetrofitClient.instance.getKec(idKota).enqueue(object : Callback<ResponseKec> {
            override fun onResponse(call: Call<ResponseKec>, response: Response<ResponseKec>) {
                val listResponse = response.body()?.kecamatan

                listIdKec.clear()
                listNameKec.clear()

                listResponse?.forEach {
                    listIdKec.add(it.id)
                    listNameKec.add(it.nama)
                }

                binding.apply {
                    spKec.onItemSelectedListener = this@SignupActivity
                    val adapter = ArrayAdapter(this@SignupActivity, R.layout.simple_spinner_dropdown_item, listNameKec)
                    spKec.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseKec>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun showKel(idKec: Int) {
        RetrofitClient.instance.getKel(idKec).enqueue(object : Callback<ResponseKel> {
            override fun onResponse(call: Call<ResponseKel>, response: Response<ResponseKel>) {
                val listResponse = response.body()?.kelurahan

                listIdKel.clear()
                listNameKel.clear()

                listResponse?.forEach {
                    listIdKel.add(it.id.toInt())
                    listNameKel.add(it.nama)
                }

                binding.apply {
                    spKel.onItemSelectedListener = this@SignupActivity
                    val adapter = ArrayAdapter(this@SignupActivity, R.layout.simple_spinner_dropdown_item, listNameKel)
                    spKel.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseKel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}