package com.ashhasib.homefinder

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_check_internet.*


class CheckInternetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_internet)


        if(isNetworkAvailable()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        btnReload.setOnClickListener {
            reloadInternet.visibility = View.VISIBLE
            Handler().postDelayed(Runnable {
                if(isNetworkAvailable()) {
                    reloadInternet.visibility = View.INVISIBLE
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else {
                    reloadInternet.visibility = View.INVISIBLE
                }
            },2000)
        }

    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
