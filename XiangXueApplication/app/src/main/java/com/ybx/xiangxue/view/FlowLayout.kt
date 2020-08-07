package com.ybx.xiangxue.view

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

/**
 * @Author 55HAITAO
 * @Date 2020/7/7 5:39 PM
 */
class FlowLayout(context: Context, attributes: AttributeSet?, defStyleAttr: Int) :
    ViewGroup(context, attributes, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    private val mHorizontalSpacing = dp2px(16)     // 水平方向上item间间隔
    private val mVerticalSpacing = dp2px(8)      // 垂直方向上item间间隔


    private var lineViews = arrayListOf<ArrayList<View>>()
    private var lineHeights = arrayListOf<Int>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        View.getDefaultSize(0, widthMeasureSpec)

        clearParam()

        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        var lineWidthUsed = 0
        var lineHeight = 0
        var views = arrayListOf<View>()

        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)      // FlowLayout 父类给的宽度
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)    // FlowLayout 父类给的高度

        var parentNeedWidth = 0    // 测量过程中要求父类需要的宽度
        var parentNeedHeight = 0   // 测量过程中要求父类需要的高度

        // 先测量子view  子view的宽高和就是父view的宽高
        // viewpager是特殊的，先测量的父view

        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val lp = childView.layoutParams

            val childWidthMeasureSpec =
                getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, lp.width)
            val childHeightMeasureSpec =
                getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, lp.height)
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)


            if (lineWidthUsed + childView.measuredWidth + mHorizontalSpacing > selfWidth) {   //  换行


                lineHeights.add(lineHeight)
                lineViews.add(views)

                parentNeedWidth = max(parentNeedWidth, lineWidthUsed )
                parentNeedHeight += lineHeight + mVerticalSpacing

                views = arrayListOf()
                lineWidthUsed = 0
                lineHeight = 0
            }

            views.add(childView)
            lineHeight = max(lineHeight, childView.measuredHeight)
            lineWidthUsed += childView.measuredWidth + mHorizontalSpacing

            if (i == childCount -1 ){ // 最后一行
                lineViews.add(views)
                lineHeights.add(lineHeight)

                parentNeedHeight += lineHeight + mVerticalSpacing
                parentNeedWidth = max(parentNeedWidth, lineWidthUsed)

            }


        }


        // 再测量自己 保存
        // 自己的大小也要根据父类给的大小来

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val realWidth = if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeedWidth
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) selfHeight else parentNeedHeight

        setMeasuredDimension(realWidth, realHeight)
    }

    private fun clearParam() {
        lineViews.clear()
        lineHeights.clear()

    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        var curL = paddingLeft
        var curT = paddingTop

        for ((index, views) in lineViews.withIndex()) {
            for (view in views) {
                val left = curL
                val top = curT
                val right = left + view.measuredWidth
                val bottom = top + view.measuredHeight
                view.layout(left, top, right, bottom)

                curL = right + mHorizontalSpacing
            }
            curT += lineHeights[index] + mVerticalSpacing
            curL = paddingLeft
        }

    }

    //    @Override
    //    protected void onDraw(Canvas canvas) {
    //        super.onDraw(canvas);
    //    }
    private fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }
}