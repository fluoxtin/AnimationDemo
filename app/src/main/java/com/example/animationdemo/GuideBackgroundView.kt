package com.example.animationdemo

import android.content.Context
import android.graphics.*
import android.util.DisplayMetrics
import android.view.View

class GuideBackgroundView(context: Context) : View(context) {

    private var paint = Paint()
    private var pointX : Float = 0f //
    private var pointY : Float = 0f //
    private var radius : Float = 0f
    val displayMetrics : DisplayMetrics = DisplayMetrics()

    init {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        if (radius > 0)
            canvas?.drawCircle(pointX, pointY, radius, paint)
    }

    fun setCircle(x : Float, y : Float, radius : Float) {
        pointX = x
        pointY = y
        this.radius = radius
        invalidate()
    }

}