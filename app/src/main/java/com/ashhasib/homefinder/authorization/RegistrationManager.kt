package com.ashhasib.homefinder.authorization

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.View
import com.ashhasib.homefinder.LoginActivity
import com.ashhasib.homefinder.R
import com.ashhasib.homefinder.model.UserProfile
import com.ashhasib.homefinder.retrofitclient.ApiClient
import com.ashhasib.homefinder.retrofitclient.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.custom_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class RegistrationManager(
    val activity: Activity,
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

                activity.progressBar.visibility = View.VISIBLE

                //val user = response.body()
                Log.d("AUTHORIZATION", "Success")
                Log.d("AUTHORIZATION", response.body().toString())


                try {
                    val user:UserProfile = response.body() as UserProfile
                    initDialogBox(user)
                    activity.progressBar.visibility = View.INVISIBLE
                } catch (e: TypeCastException) {
                    activity.progressBar.visibility = View.INVISIBLE
                    val customDialog = Dialog(activity)
                    customDialog.run {
                        setContentView(R.layout.custom_dialog)
                        this.txtDialogTitle.text = "Message"
                        this.txtDialogMessage.text = "Error creating user. Maybe a user with the same username exists. Try again"

                        this.dialogBtnOk.setOnClickListener {
                            this.dismiss()
                        }
                        this.dialogBtnCancel.setOnClickListener {
                            this.dismiss()
                        }
                        show()
                    }
                }


            }
        })

    }

    private fun initDialogBox(user:UserProfile) {

        val customDialog = Dialog(activity)
        customDialog.run {
            setContentView(R.layout.custom_dialog)
            this.txtDialogTitle.text = "Message"
            this.txtDialogMessage.text = "New user created - ${user!!.username}. Click Okay to continue"

            this.dialogBtnOk.setOnClickListener {
                activity.startActivity(Intent(context, LoginActivity::class.java))
                activity.finish()
                this.dismiss()
            }
            this.dialogBtnCancel.setOnClickListener {
                this.dismiss()
            }
            show()
        }
    }

}