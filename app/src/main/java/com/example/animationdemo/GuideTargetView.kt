package com.example.animationdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup

class GuideTargetView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var paint = Paint()
    private var radius : Float = 100f
    private var cX : Float = 10f
    private var cY : Float = 10f

    init {
        paint.color = Color.parseColor("#80000000")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        if (radius > 0) {
            canvas?.drawCircle(cX, cY, radius, paint)
        }
        paint.xfermode = null
    }

    fun setCircle(radius : Float) {
        this.radius = radius
        invalidate()
    }

    fun setCircle(pointX : Float, pointY : Float, radius: Float) {
        cX = pointX
        cY = pointY
        this.radius = radius
        invalidate()
    }

}