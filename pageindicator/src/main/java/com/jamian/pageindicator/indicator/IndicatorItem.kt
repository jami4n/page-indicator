package com.example.indicator.indicator

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.jamian.pageindicator.R


class IndicatorItem @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):View(context, attrs, defStyleAttr){  //setting up stage to build an object of IndicatorItem and returnig a view

    companion object{
        val FULL_WIDTH = LinearLayout.LayoutParams.MATCH_PARENT
    }

    var selectedIndicatorColor = 0
    var unselectedIndicatorColor = 0

    fun copy():IndicatorItem{
        var indicatorItem = IndicatorItem.Builder(this.context,
                                    this.layoutParams.height,
                                    this.layoutParams.width,
                                    (this.layoutParams as LinearLayout.LayoutParams).topMargin)
                                    .build()
        return indicatorItem
    }

    data class Builder(val context: Context,
                       var indicatorItemHeight:Int = 12,
                       var indicatorItemWidth:Int = FULL_WIDTH,
                       var indicatorItemMargin:Int = 3){ //BUilder class with context compulsory

        var selectedIndicatorColor = -1
        var unselectedIndicatorColor = -1
        var circleRadius:Int = -1
        var cornerRadius:Int = -1

        init {
            selectedIndicatorColor = context.resources.getColor(R.color.colorPrimary)
            unselectedIndicatorColor = context.resources.getColor(R.color.grey)
        }

        fun setIndicatorItemHeight(indicatorItemHeight:Int) = apply { this.indicatorItemHeight = indicatorItemHeight}
        fun setIndicatorItemWidth(indicatorItemWidth:Int) = apply { this.indicatorItemWidth = indicatorItemWidth }
        fun setIndicatorItemMargin(indicatorItemMargin:Int) = apply { this.indicatorItemMargin = indicatorItemMargin }
        fun setIndicatorAsCircle(circleRadius:Int) = apply { this.circleRadius = circleRadius }
        fun setCorners(cornerRadius:Int) = apply { this.cornerRadius = cornerRadius }

        fun setIndicatorColors(selectedIndicatorColor: Int,unselectedIndicatorColor: Int) = apply {
            this.selectedIndicatorColor = selectedIndicatorColor
            this.unselectedIndicatorColor = unselectedIndicatorColor
        }

        fun build():IndicatorItem{
            var item = IndicatorItem(context)

            item.background = context.resources.getDrawable(R.drawable.base_indicator)

            var params = LinearLayout.LayoutParams(indicatorItemWidth, indicatorItemHeight)

            if(circleRadius > -1){
                params = LinearLayout.LayoutParams(circleRadius * 2,circleRadius * 2)
                (item.background as GradientDrawable).cornerRadius = circleRadius.toFloat()

            }else if(indicatorItemWidth == FULL_WIDTH)
                params = LinearLayout.LayoutParams(indicatorItemWidth, indicatorItemHeight, 1.0f)

            else
                params = LinearLayout.LayoutParams(indicatorItemWidth, indicatorItemHeight)


            if(cornerRadius > -1){
                (item.background as GradientDrawable).cornerRadius = cornerRadius.toFloat()
            }


            var margin:Int = indicatorItemMargin
            params.setMargins(margin,margin,margin,margin)
            item.layoutParams = params

            item.selectedIndicatorColor = selectedIndicatorColor
            item.unselectedIndicatorColor = unselectedIndicatorColor

            return item
        }
    }





}