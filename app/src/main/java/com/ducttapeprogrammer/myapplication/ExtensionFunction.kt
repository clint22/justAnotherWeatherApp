package com.ducttapeprogrammer.myapplication

import android.view.View
import android.view.animation.Animation
import com.airbnb.lottie.LottieAnimationView

/**
 * Starts the lottie animation
 */
// Starts the lottie Animation
fun LottieAnimationView.startLottieAnimationView() {
    this.visibility = View.VISIBLE
    this.playAnimation()
    this.repeatCount = Animation.INFINITE
}


/**
 * Stops the lottie animation
 */
fun LottieAnimationView.stopLottieAnimationView() {
    this.visibility = View.GONE
    this.cancelAnimation()
}