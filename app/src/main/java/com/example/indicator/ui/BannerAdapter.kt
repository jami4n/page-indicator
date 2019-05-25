package com.example.indicator.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.indicator.R
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerAdapter(val context: Context, val items:List<Int>): RecyclerView.Adapter<BannerAdapter.MYVH>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MYVH {
        return MYVH(LayoutInflater.from(p0.context).inflate(R.layout.item_banner,p0,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: MYVH, p1: Int) {
        Glide.with(context).load(items[p1]).into(p0.itemView.ivBanner)
    }

    class MYVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}