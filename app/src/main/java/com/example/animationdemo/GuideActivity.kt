package com.example.animationdemo

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintSet
import com.example.animationdemo.databinding.LayoutGuideActivityBinding

class GuideActivity : AppCompatActivity() {

    lateinit var mBinding: LayoutGuideActivityBinding
    private var radius = 0f
    private var pointX = 0
    private var pointY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutGuideActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.getRoot())

        mBinding.textIKnown.setOnClickListener {
            finish()
        }

        intent.getFloatExtra("radius", 0f).apply {
                radius = this
        }
        intent.getParcelableExtra<Point>("point")?.apply {
            pointX = x
            pointY = y
        }

        mBinding.guideTargetView.setCircle(pointX.toFloat(), pointY.toFloat(), radius)
        setConstraintSet()

    }

    private fun setConstraintSet() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(mBinding.root)

        constraintSet.connect(
            R.id.arrow,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            (pointY - radius - mBinding.arrow.pivotY * 2).toInt()
        )

        constraintSet.connect(
            R.id.arrow,
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT,
            (pointX - mBinding.arrow.pivotX).toInt()
        )
        constraintSet.applyTo(mBinding.root)

    }



}