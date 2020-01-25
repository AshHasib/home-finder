package com.ashhasib.homefinder.authorization

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ashhasib.homefinder.MainActivity
import com.ashhasib.homefinder.model.Token
import com.ashhasib.homefinder.model.User
import com.ashhasib.homefinder.preference.UserSessionManager
import com.ashhasib.homefinder.retrofitclient.ApiClient
import com.ashhasib.homefinder.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginManager(val context: Activity, val user: User) {

    fun isValid(): Boolean {
        var flag = true //assuming true initially

        if (user.username.isEmpty() || user.password.isEmpty()) flag = false
        if (user.username.length <= 5 || user.password.length <= 8) flag = false
        return flag
    }


    fun authenticate() {
        val retrofit = RetrofitClientInstance.getRetrofitInstance()
        val client = retrofit.create(ApiClient::class.java)
        val call = client.getToken(User(user.username, user.password))

        call.enqueue(object : Callback<Token> {

            /**
             * Failed due to network error
             */
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("TOKEN", "onFailure")
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                context.progressBar.visibility=View.VISIBLE
                /**
                 * Authentication data is valid 200
                 */
                if (response.isSuccessful) { //authentication successful - 200

                    val token = response.body() as Token
                    Toast.makeText(context, "Authentication Successful", Toast.LENGTH_LONG).show()


                    val sessionManager = UserSessionManager(context)
                    sessionManager.write(token, user)
                    context.startActivity(Intent(context, MainActivity::class.java))
                    context.finish()
                    Log.d("TOKEN", token.token)

                }


                /**
                 * Authentication data is Invalid 400
                 */
                else {
                    Log.d("TOKEN", response.code().toString())
                    context.txtErrorMessage.visibility = View.VISIBLE
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_LONG).show()
                    context.progressBar.visibility=View.INVISIBLE
                }
            }
        })

    }

}