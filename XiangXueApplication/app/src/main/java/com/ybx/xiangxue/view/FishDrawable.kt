package com.ybx.xiangxue.view

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.animation.LinearInterpolator
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * @Author 55HAITAO
 * @Date 2020/8/6 10:44 AM
 */
class FishDrawable : Drawable() {

    private var currentValue = 0f

    // 鱼身角度:与x轴的夹角
    private var fishMainAngle = 90f

    // 鱼身透明度
    private val bodyAlpha = 110

    // 鱼其他部件透明度
    private val otherAlpha = 110

    // 鱼头半径   其他部件尺寸以此尺寸为基准
    private val headRadius = 50f


    // 鱼身体的中心点==重心
    private val middlePointF: PointF

    // 身体的长度
    private val bodyLenght = headRadius * 3.2f

    // ----------鱼鳍----------------
    // 寻找鱼鳍开始点的线长
    private val findFinsLength = headRadius * 0.9f

    // 鱼鳍的长度
    private val finsLength = headRadius * 1.3f

    // -------------鱼尾---------------
    // 尾部大圆的半径(圆心就是身体底部的中点)
    private val bigCircleRadius = headRadius * 0.7f

    // --寻找尾部中圆圆心的线长
    private val findMiddleCircleLength = bigCircleRadius * (0.6f + 1)

    // 尾部中圆的半径
    private val middleCircleRadius = bigCircleRadius * 0.6f

    // --寻找尾部小圆圆心的线长
    private val findSmallCircleLength = middleCircleRadius * (0.4f + 2.7f)

    // 尾部小圆的半径
    private val smallCircleRadius = middleCircleRadius * 0.4f

    // --寻找大三角形底边中心点的线长
    private val findTriangleLength = findSmallCircleLength


    // 画笔
    private var mPaint: Paint

    // 路径
    private var mPath: Path

    init {
        // 画笔
        mPaint = Paint().apply {
            // 颜色
            color = Color.argb(otherAlpha, 244, 92, 71)
            // 抗锯齿
            isAntiAlias = true
            // 防抖
            isDither = true
            // 画笔类型-填充
            style = Paint.Style.FILL
        }

        // 路径
        mPath = Path()

        // 中心点  以此为基准定位其他的点
        middlePointF = PointF(4.19f * headRadius, 4.19f * headRadius)


        // 动画效果
        ValueAnimator.ofFloat(0f, 10 * 360f) // 变化值
            .apply {
                duration = 15 * 1000 // 动画周期
                repeatCount = ValueAnimator.INFINITE // 无限循环
                repeatMode = ValueAnimator.RESTART   // 循环模式
                interpolator =
                    LinearInterpolator()  // 线性插值器，匀速播放    默认是AccelerateDecelerateInterpolator 加速播放
                addUpdateListener {
                    currentValue = (it.animatedValue).toString().toFloat()
                    invalidateSelf()
                }
            }.start()

    }


    override fun draw(canvas: Canvas) {
        makeFish(canvas)

    }

    private fun makeFish(canvas: Canvas) {
        Log.d("FishDrawable", sin(Math.toRadians(currentValue.toDouble())).toString())

        val fishAngle = fishMainAngle + sin(Math.toRadians(currentValue * 1.2)).toFloat() * 10f

        // 画头部
        val headPointF = caculatePointF(middlePointF, headRadius * 1.6f, fishAngle)
        canvas.drawCircle(headPointF.x, headPointF.y, headRadius, mPaint)

        // 画鱼鳍
        // 左鱼鳍
        val finsLeftPointF = caculatePointF(headPointF, findFinsLength, fishAngle + 110)
        makeFins(canvas, finsLeftPointF, false, fishAngle)
        // 右鱼鳍
        val finsRightPointF = caculatePointF(headPointF, findFinsLength, fishAngle - 110)
        makeFins(canvas, finsRightPointF, true, fishAngle)

        // 尾巴摆动的幅度和频率不一样
//        val fishSegment1Angle = fishMainAngle + sin(Math.toRadians(currentValue * 1.5)).toFloat() * 10f
        // 画节肢1
        val bigCirclePointF = caculatePointF(middlePointF, 1.6f * headRadius, fishAngle + 180)
        val middleCirclePointF = makeSegment(canvas, bigCirclePointF, true, fishAngle)
        // 画节肢2
//        val fishSegment2Angle = fishMainAngle + cos(Math.toRadians(currentValue * 1.5)).toFloat() * 20f

        makeSegment(canvas, middleCirclePointF, false, fishAngle)

        // 画鱼尾三角形
        val findEdgeLength =
            abs(sin(Math.toRadians(currentValue * 1.5)) * bigCircleRadius).toFloat()
        makeTriangle(canvas, middleCirclePointF, findTriangleLength, findEdgeLength, fishAngle)
        makeTriangle(
            canvas,
            middleCirclePointF,
            findTriangleLength - 10,
            findEdgeLength - 20,
            fishAngle
        )

        // 画身体
        makeBody(canvas, headPointF, bigCirclePointF, fishAngle)

    }

    private fun makeBody(
        canvas: Canvas,
        headPointF: PointF,
        bigCirclePointF: PointF,
        fishAngle: Float
    ) {
        val headLeftPointF = caculatePointF(headPointF, headRadius, fishAngle + 90)
        val headRightPointF = caculatePointF(headPointF, headRadius, fishAngle - 90)

        val tailLeftPointF =
            caculatePointF(bigCirclePointF, bigCircleRadius, fishAngle + 90)
        val tailRightPointF =
            caculatePointF(bigCirclePointF, bigCircleRadius, fishAngle - 90)

        val controlLeftPointF =
            caculatePointF(headPointF, 0.56f * 3.2f * headRadius, fishAngle + 130)
        val controlRightPointF =
            caculatePointF(headPointF, 0.56f * 3.2f * headRadius, fishAngle - 130)

        mPath.reset()
        mPath.moveTo(headLeftPointF.x, headLeftPointF.y)
        mPath.quadTo(controlLeftPointF.x, controlLeftPointF.y, tailLeftPointF.x, tailLeftPointF.y)
        mPath.lineTo(tailRightPointF.x, tailRightPointF.y)
        mPath.quadTo(
            controlRightPointF.x,
            controlRightPointF.y,
            headRightPointF.x,
            headRightPointF.y
        )
        mPaint.color = Color.argb(bodyAlpha, 244, 92, 71);

        canvas.drawPath(mPath, mPaint)

    }

    private fun makeTriangle(
        canvas: Canvas,
        startPointF: PointF,
        findTriangleLength:Float,
        halfTriangleLength:Float,
        fishAngle: Float
    ) {

        val fishAngle = fishAngle + cos(Math.toRadians(currentValue * 1.5)).toFloat() * 35f

//        val halfTriangleLength = middleCircleRadius + headRadius * 0.2f
        val triangleCenterPointF =
            caculatePointF(startPointF, findTriangleLength, fishAngle + 180)

        val triangleLeftPointF =
            caculatePointF(triangleCenterPointF, halfTriangleLength, fishAngle + 90)
        val triangleRightPointF =
            caculatePointF(triangleCenterPointF, halfTriangleLength, fishAngle - 90)

        mPath.reset()
        mPath.moveTo(startPointF.x, startPointF.y)
        mPath.lineTo(triangleLeftPointF.x, triangleLeftPointF.y)
        mPath.lineTo(triangleRightPointF.x, triangleRightPointF.y)
        canvas.drawPath(mPath, mPaint)
    }

    private fun makeSegment(
        canvas: Canvas,
        bigCirclePointF: PointF,
        hasBig: Boolean,
        fishAngle: Float
    ): PointF {
        val fishAngle =
            if (hasBig) fishAngle + sin(Math.toRadians(currentValue * 1.5)).toFloat() * 15f else
                fishAngle + cos(Math.toRadians(currentValue * 1.5)).toFloat() * 35f

        val bottomLeftPointF =
            caculatePointF(
                bigCirclePointF,
                if (hasBig) bigCircleRadius else middleCircleRadius,
                fishAngle + 90
            )
        val bottomRightPointF =
            caculatePointF(
                bigCirclePointF,
                if (hasBig) bigCircleRadius else middleCircleRadius,
                fishAngle - 90
            )

        val smallCirclePointF =
            caculatePointF(
                bigCirclePointF,
                if (hasBig) findMiddleCircleLength else findSmallCircleLength,
                fishAngle + 180
            )
        val upperLeftPointF =
            caculatePointF(
                smallCirclePointF,
                if (hasBig) middleCircleRadius else smallCircleRadius,
                fishAngle + 90
            )
        val upperRightPointF =
            caculatePointF(
                smallCirclePointF,
                if (hasBig) middleCircleRadius else smallCircleRadius,
                fishAngle - 90
            )

        if (hasBig) {
            canvas.drawCircle(
                bigCirclePointF.x
                , bigCirclePointF.y, bigCircleRadius, mPaint
            )
        }
        canvas.drawCircle(
            smallCirclePointF.x
            , smallCirclePointF.y, if (hasBig) middleCircleRadius else smallCircleRadius, mPaint
        )

        mPath.reset()
        mPath.moveTo(upperLeftPointF.x, upperLeftPointF.y)
        mPath.lineTo(upperRightPointF.x, upperRightPointF.y)
        mPath.lineTo(bottomRightPointF.x, bottomRightPointF.y)
        mPath.lineTo(bottomLeftPointF.x, bottomLeftPointF.y)
        canvas.drawPath(mPath, mPaint)

        return smallCirclePointF
    }

    private fun makeFins(
        canvas: Canvas,
        finsStartPointF: PointF,
        isRight: Boolean,
        fishAngle: Float
    ) {
        val finsControlAngle = 115
        // 鱼鳍结束点
        val finsEndPointF = caculatePointF(finsStartPointF, finsLength, fishAngle + 180)

        // 鱼鳍控制点
        val finsControlPointF =
            caculatePointF(
                finsStartPointF,
                finsLength * 1.8f,
                if (isRight) fishAngle - finsControlAngle else fishAngle + finsControlAngle
            )

        // 画之前一定要reset
        mPath.reset()
        // 移动到鱼鳍的起始点坐标
        mPath.moveTo(finsStartPointF.x, finsStartPointF.y)
        // 二阶贝塞尔曲线
        mPath.quadTo(finsControlPointF.x, finsControlPointF.y, finsEndPointF.x, finsEndPointF.y)

        canvas.drawPath(mPath, mPaint)


    }

    private fun caculatePointF(startPointF: PointF, length: Float, angle: Float): PointF {

        val x = cos(Math.toRadians(angle.toDouble())) * length
        val y = -sin(Math.toRadians(angle.toDouble())) * length   // android中y轴往下是正的，所以取负数

        return PointF(startPointF.x + x.toFloat(), startPointF.y + y.toFloat())

    }

    /**
     * 设置drawable透明度
     * @param alpha Int
     */
    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    /**
     * 这个值可以根据setAlpha中的值进行调整  比如alpha==0时设置TRANSPARENT  alpha==255时设置PixelFormat.OPAQUE
     * 其他时候设置PixelFormat.TRANSLUCENT
     * @return Int
     */
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT    // 只覆盖所画的内容的下一层
//        return PixelFormat.TRANSPARENT    // 透明，完全不显示任何东西
//        return PixelFormat.OPAQUE         // 完全不透明，遮盖在它下面的所有内容
    }

    /**
     * 设置一个颜色过滤器，那么在绘制出来之前，被绘制内容的每一个像素都会被颜色过滤器改变
     * @param colorFilter ColorFilter?
     */
    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    /**
     * 设置wrap_content的时候默认的高度
     * @return Int
     */
    override fun getIntrinsicHeight(): Int {
        return (headRadius * 8.38f).toInt()
    }

    /**
     * 设置wrap_content的时候默认的宽度
     * @return Int
     */
    override fun getIntrinsicWidth(): Int {
        return (headRadius * 8.38f).toInt()
    }
}