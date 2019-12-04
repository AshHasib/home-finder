package com.ashhasib.homefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        cardBtnLogin.setOnClickListener {
            loginTasks()
        }

        txtRedirectSignup.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }

    }

    private fun loginTasks() {
        val username = txtLoginUsername.text.toString()
        val password = txtLoginPassword.text.toString()
    }
}
