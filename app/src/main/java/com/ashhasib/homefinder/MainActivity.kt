package com.ashhasib.homefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ashhasib.homefinder.preference.UserSessionManager
import com.ashhasib.homefinder.retrofitclient.ApiClient
import com.ashhasib.homefinder.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var sessionManager :UserSessionManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = UserSessionManager(this)

        checkLoginStatus()



        txtMessage.text = sessionManager.tokenKey



        btn.setOnClickListener {
            if(sessionManager.isNotEmpty) {
                sessionManager.clear()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }


    private fun checkLoginStatus() {
        //val sessionManager = UserSessionManager(this)
        if(!sessionManager.isNotEmpty) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


}
