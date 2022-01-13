package com.example.animationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import com.example.animationdemo.databinding.ActivityMainBinding;
import com.example.animationdemo.databinding.AnimationLayoutBinding;
import com.example.animationdemo.databinding.MotionLayoutAirplaneBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    private ActivityMainBinding mBinding;
    private MotionLayoutAirplaneBinding mBinding;
//    private AnimationLayoutBinding mBinding;
    private MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
//        mBinding = AnimationLayoutBinding.inflate(getLayoutInflater());
        mBinding = MotionLayoutAirplaneBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
//        startAnimation(mBinding.paperAirplaneImage);

//        motionLayout = mBinding.airplane;
//        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
//            @Override
//            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
//                Log.d(TAG, "onTransitionStarted ");
//            }
//
//            @Override
//            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
//                Log.d(TAG, "onTransitionChange ");
//            }
//
//            @Override
//            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
//                Log.d(TAG, "onTransitionCompleted ");
//            }
//
//            @Override
//            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
//                Log.d(TAG, "onTransitionTrigger ");
//                // TODO: 2022/1/11 add a dialog
//            }
//        });

//        mBinding.imageView.setOnClickListener(v -> motionLayout.transitionToStart());
//        startAnimation(mBinding.smallAirplaneImage);
//        setValueAnimation(mBinding.airplane);
//        mBinding.start.setOnClickListener(v -> {
//            setValueAnimation(mBinding.airplane);
//        });
        startAnimation(mBinding.smallAirplaneImage);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startAnimation(View view) {
        //
        Animation animation = new TranslateAnimation(
                view.getX(), view.getX() + 500,
                view.getY(), view.getY() - 500
        );
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);

        ObjectAnimator translationX = ObjectAnimator
                .ofFloat(view, "translationX", view.getX(), view.getX() + 500);
        ObjectAnimator translationY = ObjectAnimator
                .ofFloat(view, "translationY", view.getY(), view.getY() - 500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(translationX, translationY);
        set.setDuration(500);
        set.start();

        
    }

    private void setValueAnimation(View view) {
        Log.d(TAG, "view left " + view.getLeft());
        Log.d(TAG, "right : " + view.getRight());
        Log.d(TAG, "top : " + view.getTop());
        Log.d(TAG, "bottom : " + view.getBottom());
        Log.d(TAG, "pivotX: " + view.getPivotX());
        Log.d(TAG, "pivotY: " + view.getPivotY());
        Log.d(TAG, "translationY: " + view.getTranslationY());
        Log.d(TAG, "View x " + view.getX() + " View y " + view.getY());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.d(TAG, "metrics width : " + displayMetrics.widthPixels);
        Log.d(TAG, "height: " + displayMetrics.heightPixels);
        Path path = new Path();
        float x = view.getLeft() + view.getPivotX();
        float y  = view.getTop() + view.getPivotY();
        path.moveTo(x + 300, y);
        path.cubicTo(
                1000 - view.getPivotX(),
                0f,
                800 - view.getPivotX(),
                0f,
                (float) displayMetrics.widthPixels / 2 - view.getPivotX(),
                (float) displayMetrics.heightPixels / 4 - view.getPivotY()
        );
//        path.arcTo(600f, 0f, 1000f, 1000f, -40f, -120f, false);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
//        animator.setDuration(1000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        ObjectAnimator rotationZ = ObjectAnimator.ofFloat(view, "rotation", 0, -34);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.6f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.6f);


        animator.start();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, rotationZ, scaleX, scaleY);
        animatorSet.setDuration(1000);
        animatorSet.start();




//        ValueAnimator valueAnimator = new ValueAnimator();
//        valueAnimator.setEvaluator((TypeEvaluator<PointF>) (fraction, startValue, endValue) -> {
//
//        });


    }

}