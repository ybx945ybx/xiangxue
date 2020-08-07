package com.example.textdraw.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

/**
 * @Author 55HAITAO
 * @Date 2020/7/23 7:29 PM
 */
class KtColorChangeTextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {

    var paint = Paint().apply {
        color = Color.RED
        textSize = 80f
        isAntiAlias = true
    }

    var linePaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 2f
    }

    val mText = "我是测试的字体"
    val mText2 = "woshighjthuo"

    val TAG = "KtColorChangeTextView"

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 中心点
        val centerX = measuredWidth / 2f
        val centerY = measuredHeight / 2f

        // 测量字体
        val textWidth = paint.measureText(mText)
        val textBounds = Rect()
        paint.getTextBounds(mText, 0, mText.length, textBounds)
        val fontMetrics = paint.fontMetrics


//        x = halfWidth - mPaint.measureText(mText)/ 2;
////        y = halfHeight + (mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics.bottom;
//        y = halfHeight- (mFontMetrics.descent + mFontMetrics.ascent) / 2;
//
//        mPaint.setColor(Color.BLACK);
//        canvas.drawText(mText, 0, mText.length(), x, y, mPaint);

        // 绘制y
//        canvas.drawLine(0, y, width, y, mLinePaint);
        Log.i(TAG, textBounds.toShortString())

        paint.textAlign = Paint.Align.LEFT
        // 字体的（x,y）
        val x = centerX - textWidth / 2
        val y1 = centerY - (fontMetrics.bottom + fontMetrics.top) / 2
//        val y2 = centerY - (fontMetrics.descent + fontMetrics.ascent) / 2

        canvas?.drawText(mText, x, y1, paint)
        paint.color = Color.BLUE
//        canvas?.drawText(mText2, x, y2, paint)

        canvas?.drawLine(centerX, 0f, centerX, measuredHeight.toFloat(), linePaint)
        canvas?.drawLine(0f, centerY, measuredWidth.toFloat(), centerY, linePaint)

        textBounds.left = (textBounds.left+x).toInt()
        textBounds.right = (textBounds.right+x).toInt()
        textBounds.top = (textBounds.top + y1).toInt()
        textBounds.bottom = (textBounds.bottom + y1).toInt()
        canvas?.drawRect(textBounds, paint)
    }
}