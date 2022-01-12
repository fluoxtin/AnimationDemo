package com.example.animationdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapRegionDecoder
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.abs
import kotlin.math.max

class ScrollingImageView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private var speed : Float  // 图片位移的速度，如果为负则方向
    private var isStart : Boolean = true

    private var lastFrameInstant : Long = -1
    private var frameTimeNano : Long = -1

    private var maxBitmapHeight : Int = 0 //view 高度
    private var bitmap : Bitmap

    private var offset : Float = 0f // 偏移量
    private var mClipBounds = Rect() // 记录边界信息

    init {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingImageView, 0, 0)
        try {
            speed = ta.getDimension(R.styleable.ScrollingImageView_Speed, 40f)
            val resourceId = ta.getResourceId(R.styleable.ScrollingImageView_source, 0)

            bitmap = getBitmap(context, resourceId)

            Log.d(TAG, "maxBitmapHeight : $maxBitmapHeight bitmap.height ${bitmap.height}")
            maxBitmapHeight = max(bitmap.height, maxBitmapHeight)
        } finally {
            ta.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), maxBitmapHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        if (!isInEditMode) {
            if (lastFrameInstant == -1L) {
                lastFrameInstant = System.nanoTime()
            }
            frameTimeNano = System.nanoTime() - lastFrameInstant  // 记录上一次刷新与这次刷新的间隔
            lastFrameInstant = System.nanoTime() // 记录当前绘制时间

            super.onDraw(canvas)

            if (canvas == null)
                return

            canvas.getClipBounds(mClipBounds)

            // 如果偏移量大于 bitmap 的宽度，重置偏移量
            while (offset < - bitmap.width)
                offset += bitmap.width

            // 绘制图片
            var left : Float = offset
            while (left < mClipBounds.width()) {
                canvas.drawBitmap(bitmap, getBimapLeft(bitmap.width.toFloat(), left), 0f, null)
                left += bitmap.width
            }

            // 计算需要的偏移量
            if (isStart && speed != 0f)
                offset -= ((abs(speed) / NANOS_PER_SECOND) * frameTimeNano).toFloat()

            postInvalidateOnAnimation()
        }
    }

    private fun getBimapLeft(layerWidth : Float, left : Float) : Float {
        return if (speed < 0)
            mClipBounds.width() - layerWidth - left
        else
            left
    }

    // 考虑使用 BitmapRegionDecoder
    private fun getBitmap(context: Context, resourceId : Int) : Bitmap  {
        val drawable = context.resources.getDrawable(resourceId, context.theme)

        val mBitmap : Bitmap
        if (drawable is BitmapDrawable) {
            mBitmap = drawable.toBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)
        } else {
            mBitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(mBitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
        }
        return mBitmap
    }

    companion object {
        const val TAG = "ScrollingImageView"
        const val NANOS_PER_SECOND = 1e9
    }
}