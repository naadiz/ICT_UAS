package com.zp.st.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.zp.st.data.login.ResponseLogin
import com.zp.st.databinding.ActivityLoginBinding
import com.zp.st.loginApi.LoginClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var profile: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        profile = getSharedPreferences(LOGIN_SESSION, MODE_PRIVATE)

        if (profile.getString("username", null) != null){
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        }

        setLogin()

        binding.apply {
            dontHaveAcc.setOnClickListener {
                val j = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(j)
            }

            lupaPassword.setOnClickListener {
                val k = Intent(this@LoginActivity, ForgotActivity::class.java)
                startActivity(k)
            }
        }
    }

    private fun setLogin() {
        binding.apply {
            btnLogin.setOnClickListener {
                username = etUsername.text.toString().trim()
                password = etPassword.text.toString().trim()

                when {
                    username == "" -> {
                        Toast.makeText(this@LoginActivity, "Tidak boleh ada kolom yang kosong", Toast.LENGTH_SHORT)
                            .show()
                    }
                    password == "" -> {
                        Toast.makeText(this@LoginActivity, "Tidak boleh ada kolom yang kosong", Toast.LENGTH_SHORT)
                            .show()
                    } else -> {
                        getData()
                    }
                }
            }
        }
    }

    private fun getData(){
        val api = LoginClient().getInstanse()
        api.loginTest(username, password).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful){
                    if (response.body()?.response == true){
                        getSharedPreferences(LOGIN_SESSION, MODE_PRIVATE)
                            .edit()
                            .putString("username", response.body()?.payloadLogin?.username)
                            .putString("nama_depan", response.body()?.payloadLogin?.namaDepan)
                            .putString("nama_belakang", response.body()?.payloadLogin?.namaBelakang)
                            .putString("jenis_kelamin", response.body()?.payloadLogin?.jenisKelamin)
                            .putString("email", response.body()?.payloadLogin?.email)
                            .putString("no_handphone", response.body()?.payloadLogin?.noHandphone)
                            .putString("id_user", response.body()?.payloadLogin?.idUser)
                            .putString("tinggi_user", response.body()?.payloadLogin?.tinggiUser)
                            .putString("berat_user", response.body()?.payloadLogin?.beratUser)
                            .putString("usia_user", response.body()?.payloadLogin?.usiaUser)
                            .putString("riwayat_user", response.body()?.payloadLogin?.riwayatUser)
                            .putString("password", password)
                            .apply()

                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login gagal karena kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("Pesan Error : ", "${t.message}")
            }
        })
    }

    companion object{
        const val LOGIN_SESSION = "login_session"
    }
}