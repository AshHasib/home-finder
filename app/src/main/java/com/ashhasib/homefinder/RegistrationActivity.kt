package com.ashhasib.homefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ashhasib.homefinder.authorization.RegistrationManager
import com.ashhasib.homefinder.model.UserProfile
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        cardBtnRegister.setOnClickListener {
            register()
        }



    }



    private fun register() {
        val fullName = txtSignupFullName.text.toString().trim()
        val email = txtSignupEmail.text.toString().trim()
        val phoneNumber = ""
        val username = txtSignupUsername.text.toString().trim()
        val password = txtSignupPassword.text.toString().trim()
        val repass = txtSignupRePassword.text.toString().trim()

        val userProfile = UserProfile(
            fullName,username, email, phoneNumber
        )
        userProfile.password = password

        val registrationManager = RegistrationManager(this, userProfile)


        if(!registrationManager.isValid()) {
            txtSignupErrorMessage.visibility = View.VISIBLE
            txtSignupErrorMessage.text = "One of the fields left blank"
        }
        else if(!registrationManager.isMatchPassword(repass)) {
            txtSignupErrorMessage.visibility = View.VISIBLE
            txtSignupErrorMessage.text = "Passwords do not match"
        }

        else {
            txtSignupErrorMessage.visibility = View.INVISIBLE
            registrationManager.authenticate()
        }

    }

}
