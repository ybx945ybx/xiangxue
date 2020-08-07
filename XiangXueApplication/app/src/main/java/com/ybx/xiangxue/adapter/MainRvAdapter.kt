package com.ybx.xiangxue.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ybx.xiangxue.R
import com.ybx.xiangxue.activity.FishActivity
import com.ybx.xiangxue.activity.FlowLayoutActivity
import com.ybx.xiangxue.activity.TextViewActivity

/**
 * @Author 55HAITAO
 * @Date 2020/8/6 1:52 PM
 */
class MainRvAdapter(private val mContext: Context, var mData: MutableList<String>?) :
    RecyclerView.Adapter<MainRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainRvViewHolder(
        LayoutInflater.from(mContext).inflate(R.layout.item_rv_main, parent, false)
    )

    override fun getItemCount() = mData?.size ?: 0

    override fun onBindViewHolder(holder: MainRvViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> mContext.startActivity(Intent(mContext, TextViewActivity::class.java))
                1 -> mContext.startActivity(Intent(mContext, FlowLayoutActivity::class.java))
                2 -> mContext.startActivity(Intent(mContext, FishActivity::class.java))
            }
        }

        with(mData?.get(position)) {
            holder.tvTitle.text = this
        }
    }

}

class MainRvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
}



