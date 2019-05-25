package com.example.indicator.indicator

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import android.view.View
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.graphics.drawable.GradientDrawable
import com.jamian.pageindicator.R


class Indicator @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {


    var lastKnownPosition = 0
    internal var selectedIndicatorColor = 0
    internal var unselectedIndicatorColor = 0

    init {
        selectedIndicatorColor = context.resources.getColor(R.color.salmon)
        unselectedIndicatorColor = context.resources.getColor(R.color.grey)
    }
/*
    fun selectedIndicatorColor(selectedIndicatorColor:Int){
        this.selectedIndicatorColor = selectedIndicatorColor
    }

    fun unselectedIndicatorColor(unselectedIndicatorColor:Int){
        this.unselectedIndicatorColor = unselectedIndicatorColor
    }*/

    fun attachToRecyclerView(recyclerView:RecyclerView,indicatorItem: IndicatorItem = defaulIndicatorItem()){

        var recylerViewItemCount = recyclerView.adapter?.itemCount?:0

        this.removeAllViews()

        for (v in 1..recylerViewItemCount){
            this.addView(indicatorItem.copy())
        }

        selectedIndicatorColor = indicatorItem.selectedIndicatorColor
        unselectedIndicatorColor = indicatorItem.unselectedIndicatorColor

        setSelectedIndicator(0)


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val newPosition = (recyclerView.getLayoutManager() as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

                    if(newPosition != -1)
                    setSelectedIndicator(newPosition)
                }
            }
        })

    }

    private fun defaulIndicatorItem(): IndicatorItem {
        return IndicatorItem.Builder(context).build()
    }

    private fun setSelectedIndicator(newPosition:Int){

        for(v in 0..childCount - 1){
            if(v == newPosition)
                (getChildAt(v)?.background as GradientDrawable).setColor(selectedIndicatorColor)
            else
                (getChildAt(v)?.background as GradientDrawable).setColor(unselectedIndicatorColor)
        }

        try{

            if(newPosition!=lastKnownPosition){

                if(lastKnownPosition > newPosition){
                    val s = ScaleAnimation(1.5f,1f,1f,1f,Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f)
                    s.duration = 100
                    getChildAt(newPosition).startAnimation(s)
                }else{
                    val s = ScaleAnimation(1.5f,1f,1f,1f,Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f)
                    s.duration = 100
                    getChildAt(newPosition).startAnimation(s)
                }
            }

        }catch (e:Exception){}


        lastKnownPosition = newPosition

    }

    /*private fun createIndicatorItem():View{

        var view = View(context)
        view.background = context.resources.getDrawable(R.drawable.grey_indicator)

        var params = LayoutParams(20, 4)

        if(itemWidth == LayoutParams.MATCH_PARENT)
            params = LayoutParams(itemWidth, 8,1.0f)
        else
            params = LayoutParams(itemWidth, 8)

        var margin:Int = 2
        params.setMargins(margin,margin,margin,margin)
        view.layoutParams = params

        return view
    }*/


}