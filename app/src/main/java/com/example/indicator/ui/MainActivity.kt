package com.example.indicator.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import com.example.indicator.R
import com.example.indicator.indicator.IndicatorItem
import com.jamian.indicator.Log

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var items:List<Int> = listOf(R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5,R.drawable.item6)

        rvBanner.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        rvBanner.adapter = BannerAdapter(this,items)
        var snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvBanner);

        var indicatorItem = IndicatorItem.Builder(this).build()


        indicator.attachToRecyclerView(rvBanner,indicatorItem)
    }
}
