package com.zp.st.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zp.st.data.MyMarket
import com.zp.st.R
import com.zp.st.databinding.ListMarketBinding

class MarketListAdapter :
    RecyclerView.Adapter<MarketListAdapter.MarketListHolder>() {
    lateinit var listMarket: ArrayList<MyMarket>

    fun setData(listMarket: ArrayList<MyMarket>) {
        this.listMarket = listMarket
    }

    class MarketListHolder(private val binding: ListMarketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listMarket: MyMarket) {
            binding.apply {
                tvMarket.text = listMarket.productName
                tvSub.text = listMarket.productPreview

                Glide.with(itemView).load(listMarket.marketUrl).placeholder(R.drawable.image_one_history).dontAnimate().into(ivMarket);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketListHolder {
        return MarketListHolder(ListMarketBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarketListHolder, position: Int) {
        holder.bindData(listMarket[position])
    }

    override fun getItemCount(): Int = listMarket.size
}