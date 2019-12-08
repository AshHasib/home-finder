package com.ashhasib.homefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ashhasib.homefinder.authorization.LoginManager
import com.ashhasib.homefinder.model.Token
import com.ashhasib.homefinder.model.User
import com.ashhasib.homefinder.retrofitclient.ApiClient
import com.ashhasib.homefinder.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        cardBtnLogin.setOnClickListener {
            loginTasks()
        }


        /**
         * Redirecting to sign up page for a new account
         */
        txtRedirectSignup.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

    }


    private fun loginTasks() {
        val username = txtLoginUsername.text.toString().trim()
        val password = txtLoginPassword.text.toString().trim()

        val manager = LoginManager(context = this, user= User(username, password))

        if (manager.isValid()) {
            txtErrorMessage.visibility = View.INVISIBLE

            manager.authenticate()

        } else {
            txtErrorMessage.visibility = View.VISIBLE
        }
    }


}
