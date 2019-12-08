package com.ashhasib.homefinder.authorization

import android.content.Context
import android.util.Log
import com.ashhasib.homefinder.model.User
import com.ashhasib.homefinder.model.UserProfile
import com.ashhasib.homefinder.retrofitclient.ApiClient
import com.ashhasib.homefinder.retrofitclient.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class RegistrationManager(
    val context: Context,
    val userProfile: UserProfile
) {


    fun isValid(): Boolean {
        var flag = true
        if (userProfile.fullName.isEmpty() ||
            userProfile.username.isEmpty() ||
            userProfile.email.isEmpty() ||
            userProfile.password.isEmpty()
        ) flag = false


        return flag
    }

    fun isMatchPassword(repass: String): Boolean {
        if (userProfile.password.equals(repass)) {
            return true
        }

        return false
    }

    fun authenticate() {


        val retrofit = RetrofitClientInstance.getRetrofitInstance()
        val client = retrofit.create(ApiClient::class.java)
        val call = client.createUser(userProfile)

        call.enqueue(object : Callback<UserProfile> {


            /**
             * Error due to network failure or something
             */
            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                Log.d("AUTHORIZATION", "Failure")
            }


            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                //val user = response.body()
                Log.d("AUTHORIZATION", "Success")
                Log.d("AUTHORIZATION", response.body().toString())


            }
        })

    }

}