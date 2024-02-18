package com.zp.st.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zp.st.R
import com.zp.st.data.MyArticle
import com.zp.st.databinding.ListArticleBinding

class ArticleAdapter :
    RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {
    lateinit var listArticle: ArrayList<MyArticle>

    fun setData(listArticle: ArrayList<MyArticle>) {
        this.listArticle = listArticle
    }

    class ArticleHolder(private val binding: ListArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(listArticle: MyArticle) {
            binding.apply {
                tvArticle.text = listArticle.title
                tvSub.text = listArticle.preview

                Glide.with(itemView).load(listArticle.articleImageUrl).placeholder(R.drawable.ic_launcher_background).dontAnimate().into(ivArticle);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(ListArticleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bindData(listArticle[position])
    }

    override fun getItemCount(): Int = listArticle.size
}