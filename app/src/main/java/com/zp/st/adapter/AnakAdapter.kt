package com.zp.st.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zp.st.R
import com.zp.st.ResponseModel

class AnakAdapter(
    private val context: Context,
    private val dataList: ArrayList<ResponseModel>
) : RecyclerView.Adapter<AnakAdapter.AnakHolder>() {
    class AnakHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvDataAnak = view.findViewById<TextView>(R.id.tv_data_anak)
        val tvNamaAnak = view.findViewById<TextView>(R.id.tv_nama_anak)
        val tvJenisKelaminAnak = view.findViewById<TextView>(R.id.tv_jenis_kelamin_anak)
        val tvTtlAnak = view.findViewById<TextView>(R.id.tv_ttl_anak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnakHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.list_data_anak, parent, false)
        return AnakHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnakHolder, position: Int) {
        holder.tvDataAnak.text = "Data anak ke-" + dataList.get(position).anakKe
        holder.tvNamaAnak.text = dataList.get(position).namaAnak
        holder.tvJenisKelaminAnak.text = dataList.get(position).jenisKelaminAnak
        holder.tvTtlAnak.text = dataList.get(position).tempatLahir + ", " + dataList.get(position).tanggalLahir
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: ArrayList<ResponseModel>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}