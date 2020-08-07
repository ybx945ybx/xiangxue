package com.ybx.xiangxue.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.text.MeasuredText
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView

/**
 * @Author 55HAITAO
 * @Date 2020/7/22 11:23 AM
 */
class ColorChangeTextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {

    private val mText = "可变色的文字"
    private var percent = 0f

    private var paint: Paint = Paint().apply {
        color = Color.RED
        strokeWidth = 2f
        textSize = 80f
//        textAlign = Paint.Align.CENTER

    }

    fun setPercent(percent: Float) {
        this.percent = percent
        invalidate()
    }

    fun getPercent() = percent


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawCenterLineX(canvas)
        drawCenterLineY(canvas)

        val des = paint.fontMetrics.descent
        val asc = paint.fontMetrics.ascent
        val fontSpacing = paint.fontSpacing


        val textWidth = paint.measureText(mText)


        paint.color = Color.BLACK
        val left = width / 2f
        var baseLine = height / 2f - (des + asc) / 2f

        val path: Path = Path().apply {
            lineTo(1000f, 500f)
//            lineTo(100f, 400f)
        }
        canvas?.drawPath(path, paint)

        canvas?.drawTextOnPath(mText, path, 150f, 110f, paint)
        baseLine += fontSpacing

        canvas?.drawTextRun(mText, 0, 6, 0, 6, left, baseLine, false, paint)
        baseLine += fontSpacing

        canvas?.drawText(mText, left, baseLine, paint)
        baseLine += fontSpacing
        canvas?.drawText(mText, left, baseLine, paint)
        baseLine += fontSpacing
        canvas?.drawText(mText, left, baseLine, paint)
        baseLine += fontSpacing
        canvas?.drawText("a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz", left, baseLine, paint)


//        第一层
        canvas?.save()
        canvas?.clipRect(left + percent * textWidth, 0f, left + textWidth, measuredHeight.toFloat())
        canvas?.drawText(mText, left, height / 2 - (des - asc) / 2, paint)
        canvas?.restore()

        paint.color = Color.RED

//        第二层
        canvas?.save()

        canvas?.clipRect(left, 0f, left + percent * textWidth, measuredHeight.toFloat())
        canvas?.drawText(mText, left, height / 2 - (des - asc) / 2, paint)
        canvas?.restore()




        baseLine += fontSpacing
        val staticLayout = StaticLayout.Builder.obtain(
            "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz",
            left.toInt(),
            800,
            TextPaint().apply {
                color = Color.RED
                strokeWidth = 2f
                textSize = 80f
                textAlign = Paint.Align.CENTER
            },
            600
        )

    }

    private fun drawCenterLineX(canvas: Canvas?) {
        canvas?.drawLine(0F, height / 2F, width.toFloat(), height / 2F, paint)
    }

    private fun drawCenterLineY(canvas: Canvas?) {
        canvas?.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), paint)
    }
}