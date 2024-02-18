package com.zp.st.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zp.st.activity.*
import com.zp.st.data.MyAnnounce
import com.zp.st.data.MyArticle
import com.zp.st.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var listArticleData : ArrayList<MyArticle>
    lateinit var listAnnounceData : ArrayList<MyAnnounce>
    private lateinit var profile: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()
        setUpDataAnnounce()
        setUpAdapter()
        setUpAdapterTwo()
        setUpAction()
        scrollHideAndShowTabLayout()


        profile = requireActivity().getSharedPreferences(
            ProfileFragment.LOGIN_SESSION,
            Context.MODE_PRIVATE
        )

//        binding.tvLogin.text = "Hi, ${profile.getString("nama_depan", null) + " " + profile.getString("nama_belakang", null)}!"
    }

    private fun setUpDataAnnounce() {
        listAnnounceData = arrayListOf(
            MyAnnounce(
                "",
                "")
        )
    }

    private fun setUpAction() {
//        binding.tvLogin.setOnClickListener {
//            val i = Intent(activity, LoginActivity::class.java)
//            startActivity(i)
//        }
//
//        binding.tvMarket.setOnClickListener {
//            val k = Intent(activity, MarketActivity::class.java)
//            startActivity(k)
//        }
//
//        binding.btnMarket.setOnClickListener {
//            val k = Intent(activity, MarketActivity::class.java)
//            startActivity(k)
//        }
//
//        binding.btnSes.setOnClickListener {
//            val o = Intent(activity, ChatActivity::class.java)
//            startActivity(o)
//        }
//        binding.btnSds.setOnClickListener {
//            val x = Intent(activity, DetectionActivity::class.java)
//            startActivity(x)
//        }
//        binding.btnSps.setOnClickListener {
//            val n = Intent(activity, PredictionActivity::class.java)
//            startActivity(n)
//        }
    }

    private fun setUpData() {
        listArticleData = arrayListOf(
            MyArticle(
                "https://asset.kompas.com/crops/TqgfxHgyd-7kmbLGG_zxbBl7yYU=/15x15:985x662/1200x800/data/photo/2020/12/18/5fdc661e1b2a5.jpg",
                "7 Makanan yang Tinggi \nZat Besi",
                "19/08/2023, 12.30 WIB"
            ),
            MyArticle(
                "https://sumeks.disway.id/upload/3b048823caf677f0cea8c356314b763f.jpeg",
                "Catat 5 Manfaat Buah Matoa \nUntuk Kesehatan, \nJangan Lewatkan!",
                "19/08/2023, 12.25 WIB"
            ),
            MyArticle(
                "https://akcdn.detik.net.id/visual/2023/08/10/ilustrasi-hamil-makan-3_169.jpeg?w=750&q=90",
                "Manfaat Telur Puyuh untuk \nIbu Hamil: Apakah \nBenar-benar Bisa Menambah \nBerat Badan Janin?",
                "19/08/2023, 12.23 WIB"
            )
        )
    }

    private fun setUpAdapter() {
//        val adapterAnnounce = AnnounceAdapter()
//        adapterAnnounce.setData(listAnnounceData)
//        val linearLayout = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//
//        binding.rvAds.apply {
//            layoutManager = linearLayout
//            adapter = adapterAnnounce
//        }
    }

    private fun setUpAdapterTwo() {
//        val adapterArticle = ArticleAdapter()
//        adapterArticle.setData(listArticleData)
//        val linearLayout = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//
//        binding.rvArticle.apply {
//            layoutManager = linearLayout
//            adapter = adapterArticle
//        }
    }

    private fun scrollHideAndShowTabLayout() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            binding.consLay.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//                val x = scrollY - oldScrollY
//                if (x < 0) {
//                    (activity as HomeActivity?)?.showTabLayout()
//                } else if (x > 0) {
//                    (activity as HomeActivity?)?.hideTabLayout()
//                }
//            }
//        }
    }

    companion object {
        fun newInstance(page: Int) = HomeFragment()
    }
}