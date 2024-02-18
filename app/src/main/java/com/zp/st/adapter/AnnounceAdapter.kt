package com.zp.st.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zp.st.data.MyAnnounce
import com.zp.st.databinding.ListAnnounceBinding

class AnnounceAdapter :
    RecyclerView.Adapter<AnnounceAdapter.AnnounceHolder>() {
    lateinit var listAnnounce: ArrayList<MyAnnounce>

    fun setData(listAnnounce: ArrayList<MyAnnounce>) {
        this.listAnnounce = listAnnounce
    }

    class AnnounceHolder(private val binding: ListAnnounceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listAnnounce: MyAnnounce) {
            binding.apply {
                tvArticle.text = listAnnounce.title
                tvSub.text = listAnnounce.preview
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnounceHolder {
        return AnnounceHolder(ListAnnounceBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AnnounceHolder, position: Int) {
        holder.bindData(listAnnounce[position])
    }

    override fun getItemCount(): Int = listAnnounce.size
}