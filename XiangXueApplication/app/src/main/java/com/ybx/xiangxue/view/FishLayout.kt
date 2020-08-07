package com.ybx.xiangxue.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.RelativeLayout

/**
 * @Author 55HAITAO
 * @Date 2020/8/6 7:56 PM
 */
class FishLayout(context: Context, attributeSet: AttributeSet) :
    RelativeLayout(context, attributeSet) {

    private var ivFish: ImageView = ImageView(context).apply {
        setImageDrawable(FishDrawable())
    }
    private var touchX = 0f
    private var touchY = 0f
    private var mPaint: Paint
    private var mAlpha: Int = 0
    private var ripple: Float = 0f

    init {
        // viewgroup默认不执行ondraw
        setWillNotDraw(false)
        addView(ivFish)

        mPaint = Paint().apply {
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeWidth = 8f
            color = Color.parseColor("#F67052")
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.alpha = mAlpha
        canvas?.drawCircle(touchX, touchY, ripple * 100, mPaint)

        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        touchX = event!!.x
        touchY = event.y
        mPaint.alpha = 100
        ObjectAnimator.ofFloat(this, "ripple", 0f, 1f).setDuration(1000).start()


        return super.onTouchEvent(event)
    }

    fun setRipple(ripple: Float) {
        mAlpha = (100 * (1 - ripple)).toInt()
        this.ripple = ripple
    }

    fun getRipple() = ripple

}