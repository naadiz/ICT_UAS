package com.zp.st.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.zp.st.R
import com.zp.st.databinding.ListHistoryBinding
import com.zp.st.fragment.ProfileFragment
import com.zp.st.history.ResponseModelHistoryItem

class HistoryAdapter(private val context: Context,
                     private val dataList: ArrayList<ResponseModelHistoryItem>)
    : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    private lateinit var profile: SharedPreferences

    class HistoryHolder(private val binding: ListHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val theDate: TextView = itemView.findViewById(R.id.history_date)
        val theName: TextView = itemView.findViewById(R.id.get_name)
        val theGender: TextView = itemView.findViewById(R.id.get_gender)
        val theDob: TextView = itemView.findViewById(R.id.get_dob)
        val theTbBb: TextView = itemView.findViewById(R.id.get_tb_bb)
        val theImage: CardView = itemView.findViewById(R.id.cv_history)

        val titleName: TextView = itemView.findViewById(R.id.tv_title_nama)
        val titleGender: TextView = itemView.findViewById(R.id.tv_title_gender)
        val titleDob: TextView = itemView.findViewById(R.id.tv_title_dob)
        val titleTbBb: TextView = itemView.findViewById(R.id.tv_title_tb_bb)

        val stuntingLayout: ConstraintLayout = itemView.findViewById(R.id.btn_detected)

        val tvStn: TextView = itemView.findViewById(R.id.tv_stn)
        val tvNotStn: TextView = itemView.findViewById(R.id.tv_not_stn)
        val btnDetectedAction: AppCompatButton = itemView.findViewById(R.id.btn_detected_action)

        fun bindData(listHistory: ResponseModelHistoryItem) {
            binding.apply {
                historyDate.text = "2023-11-29"
                getName.text = listHistory.namaAnak
                getGender.text = listHistory.jenisKelaminAnak
                getDob.text = "2023-11-20"
                getTbBb.text = listHistory.hasil + "cm/" + listHistory.berat + "kg"

//                if (listHistory.status == "normal" || listHistory.status == "Normal"){
//                    tvStn.visibility = View.GONE
//                    btnDetectedAction.visibility = View.GONE
//                    tvNotStn.visibility = View.VISIBLE
//                    stuntingLayout.background = itemView.resources.getDrawable(R.drawable.button_shape_green)
//                } else {
//                    tvStn.visibility = View.VISIBLE
//                    btnDetectedAction.visibility = View.VISIBLE
//                    tvNotStn.visibility = View.GONE
//                    stuntingLayout.background = itemView.resources.getDrawable(R.drawable.button_shape_red)
//                }

                if (listHistory.hasil.toFloat() >= 49.0){
                    tvStn.visibility = View.GONE
                    btnDetectedAction.visibility = View.GONE
                    tvNotStn.visibility = View.VISIBLE
                    stuntingLayout.background = itemView.resources.getDrawable(R.drawable.button_shape_green)
                } else {
                    tvStn.visibility = View.VISIBLE
                    btnDetectedAction.visibility = View.VISIBLE
                    tvNotStn.visibility = View.GONE
                    stuntingLayout.background = itemView.resources.getDrawable(R.drawable.button_shape_red)
//                    tvStn.text = "Terdeteksi stunting, \\ndiduga karena faktor\\n ibu (${})"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        profile = context.getSharedPreferences(ProfileFragment.LOGIN_SESSION, Context.MODE_PRIVATE)
        return HistoryHolder(ListHistoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bindData(dataList[position])

        val currentItem = dataList[position]
        val isVisible: Boolean = currentItem.visibility

        holder.theName.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.theGender.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.theDob.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.theTbBb.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.theImage.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.titleName.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.titleGender.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.titleDob.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.titleTbBb.visibility = if (isVisible) View.VISIBLE else View.GONE
        holder.stuntingLayout.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.tvStn.text = "Terdeteksi stunting, diduga karena faktor ibu (${profile.getString("riwayat_user", null)})"

        holder.theDate.setOnClickListener {
            currentItem.visibility = !currentItem.visibility
            notifyItemChanged(position)
        }

        holder.btnDetectedAction.setOnClickListener {
//            Toast.makeText(context, dataList[position].namaAnak, Toast.LENGTH_SHORT).show()
//            Toast.makeText(context, "Jethro Fourier", Toast.LENGTH_SHORT).show()
            val k = Intent(context, RujukanActivity::class.java)
            context.startActivity(k)
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(data: ArrayList<ResponseModelHistoryItem>){
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }
}