package com.example.animationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.example.animationdemo.databinding.ActivityMainBinding;
import com.example.animationdemo.databinding.MotionLayoutAirplaneBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    private ActivityMainBinding mBinding;
    private MotionLayoutAirplaneBinding mBinding;
    private MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mBinding = MotionLayoutAirplaneBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
//        startAnimation(mBinding.paperAirplaneImage);

        motionLayout = mBinding.airplane;
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
                Log.d(TAG, "onTransitionStarted ");
            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                Log.d(TAG, "onTransitionChange ");
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                Log.d(TAG, "onTransitionCompleted ");
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
                Log.d(TAG, "onTransitionTrigger ");
                // TODO: 2022/1/11 add a dialog
            }
        });

        mBinding.imageView.setOnClickListener(v -> motionLayout.transitionToStart());
//        startAnimation(mBinding.smallAirplaneImage);
        setValueAnimation(mBinding.smallAirplaneImage);

    }

    private void startAnimation(View view) {
        //
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(view, "translationY", 50f)
                .setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    private void setValueAnimation(View view) {
        Path path = new Path();
//        path.arcTo(0f, 0f, 1000f, 1000f, -270f, -100f, true);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, );
        animator.setDuration(1500);
        animator.start();
    }

}