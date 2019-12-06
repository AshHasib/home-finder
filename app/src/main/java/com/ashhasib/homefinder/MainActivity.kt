package com.ashhasib.homefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ashhasib.homefinder.api_interface.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivity(Intent(this, LoginActivity::class.java))

        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiClient = retrofit.create(ApiClient::class.java)

        val call = apiClient.helloMessage

        call.enqueue(object :Callback<String>{

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("RETROFIT", t.stackTrace.toString())
                Log.d("RETROFIT", t.message)
                Log.d("RETROFIT", "FAIL")

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {

                if( response.isSuccessful ){
                    txtMessage.setText(response.body())
                    Log.d("RETROFIT", response.message())
                    Log.d("RETROFIT", "SUCCESS")
                }
            }

        })



    }
}
