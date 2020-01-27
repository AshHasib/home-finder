package com.ashhasib.homefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val anim = AlphaAnimation(0.0f, 1.0f)

        anim.duration = 1200

        imgLogo.animation = anim

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, CheckInternetActivity::class.java))
            finish()
        },1800)
    }
}
