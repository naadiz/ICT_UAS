package com.zp.st.fragment

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidmads.library.qrgenearator.QRGEncoder
import androidx.annotation.RequiresApi
import androidx.core.graphics.set
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.zp.st.api.service.ApiBarcodeClient
import com.zp.st.databinding.FragmentBarcodeBinding
import com.zp.st.fetchbarcode.ResponseBarcode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BarcodeFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBarcodeBinding
    lateinit var qrEncoder: QRGEncoder
    lateinit var bitmap: Bitmap
    private lateinit var profile: SharedPreferences

    private var listIdAnak = ArrayList<Int>()
    private var listNameAnak = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBarcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile = requireActivity().getSharedPreferences(
            ProfileFragment.LOGIN_SESSION,
            Context.MODE_PRIVATE
        )

        showAnak()

//        val activity: AppCompatActivity? = requireActivity() as? AppCompatActivity
//
//        activity?.let {
//            val windowManager: WindowManager = it.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            val display: Display = windowManager.defaultDisplay
//            val point: Point = Point()
//            val width = point.x
//            val height = point.y
//
//            var dimen = if (width < height) width else height
//            dimen = dimen * 3 / 4
//
//            qrEncoder = QRGEncoder(profile.getString("id", null), null, QRGContents.Type.TEXT, dimen)
//
//            try {
//                bitmap = qrEncoder.bitmap
//                binding.qrId.setImageBitmap(bitmap)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(profile.getString("id_user", null),
                BarcodeFormat.QR_CODE,
                400,
                400
            )

            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width){
                for (y in 0 until height){
                    bmp.set(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }

            binding.qrId.setImageBitmap(bmp)
        } catch (e: WriterException){
            e.printStackTrace()
        }
    }

    private fun showAnak() {
        ApiBarcodeClient.instance.getBarcode(profile.getString("id_user", null)!!.toInt())
            .enqueue(object : Callback<ResponseBarcode>{
                override fun onResponse(
                    call: Call<ResponseBarcode>,
                    response: Response<ResponseBarcode>
                ) {
                    val listResponseBarcode = response.body()?.dataAntro
                    listResponseBarcode?.forEach {
                        listIdAnak.add(it.idAnak.toInt())
                        listNameAnak.add(it.namaAnak)
                    }

                    binding.apply {
                        spNamaAnak.onItemSelectedListener = this@BarcodeFragment
                        val adapter  = ArrayAdapter(context!!, R.layout.simple_spinner_dropdown_item, listNameAnak)
                        spNamaAnak.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<ResponseBarcode>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    companion object {
        fun newInstance(page: Int) = BarcodeFragment()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
        binding.apply {
            if (parent?.selectedItem == spNamaAnak.selectedItem){
                val writer = QRCodeWriter()
                try {
                    val bitMatrix = writer.encode("https://pengukuran.esdsugm.id/pages/hasil-pengukuran.php?id_user=${profile.getString("id_user", null)}&id_anak=${listIdAnak[position].toString()}",
                        BarcodeFormat.QR_CODE,
                        400,
                        400
                    )

                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

                    for (x in 0 until width){
                        for (y in 0 until height){
                            bmp.set(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }

                    binding.qrId.setImageBitmap(bmp)
                } catch (e: WriterException){
                    e.printStackTrace()
                }

//                Toast.makeText(activity, listIdAnak[position].toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}