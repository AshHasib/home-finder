package com.ashhasib.homefinder.authorization

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ashhasib.homefinder.CreatePostActivity
import com.ashhasib.homefinder.fragments.UploadBottomSheet
import com.ashhasib.homefinder.model.RentPost
import com.ashhasib.homefinder.model.UploadImage
import com.ashhasib.homefinder.model.UserProfile
import com.ashhasib.homefinder.preference.UserSessionManager
import com.ashhasib.homefinder.retrofitclient.ApiClient
import com.ashhasib.homefinder.retrofitclient.RetrofitClientInstance
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_create_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ClassCastException
import java.lang.NumberFormatException

class CreatePostManager(val activity: Activity) {




    fun partOneIsValid(description:String, area:String, rent:String) :Boolean {

        if(description.isEmpty() || description.length<10) return false
        if(area.isEmpty()) return false

        try {
            rent.toInt()
        } catch (e:NumberFormatException) {
            return false
        }
        return true
    }



    fun partTwoIsValid(numBedroom:String, numBathroom:String, numFloor:String) :Boolean {

        try {
            numBedroom.toInt()
            numBathroom.toInt()
            numFloor.toInt()
        } catch (e:NumberFormatException) {
            return false
        }

        return true
    }







    fun post(rentPost: RentPost,
             fileUriList:ArrayList<Uri>,
             fileNameList:ArrayList<String>,
             imgRef:String) {


        val token = UserSessionManager(activity).tokenKey
        /**
         * Uploading post to API
         */
        val retrofit = RetrofitClientInstance.getRetrofitInstance()
        val client = retrofit.create(ApiClient::class.java)
        val call = client.postRent("Token ${token}",rentPost)

        call.enqueue(object : Callback<RentPost>{
            override fun onFailure(call: Call<RentPost>, t: Throwable) {

            }

            override fun onResponse(call: Call<RentPost>, response: Response<RentPost>) {

                try {
                    val returnRentPost:RentPost = response.body() as RentPost
                    Toast.makeText(activity, "Uploaded ${returnRentPost.imageRef}", Toast.LENGTH_LONG).show()
                } catch (e: TypeCastException) {
                    Toast.makeText(activity, "Could not cast: ${e.toString()}", Toast.LENGTH_LONG).show()
                }
            }

        })


        /**
         * Uploading images to Firebase Storage
         */
        val mainStorageRef = FirebaseStorage.getInstance().getReference("img")
        val databaseReference=
            FirebaseDatabase.getInstance().reference.child("img").child(imgRef)

        for(i in 0 until fileUriList.size) {

            val fUri = fileUriList[i]
            val fRef = mainStorageRef.child(imgRef).child(fileNameList[i])
            val uploadTask = fRef.putFile(fUri)
                .addOnSuccessListener {
                    /**
                     * Setting the image links to RT Database
                     */
                    val upImg = UploadImage(
                        fileNameList[i],
                        it.metadata!!.reference!!.downloadUrl.toString())
                    val upId = databaseReference.push().key.toString()
                    databaseReference.child(upId).setValue(upImg)

                }
                .addOnFailureListener{
                    Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {
                   Toast.makeText(activity, "Uploading", Toast.LENGTH_LONG).show()
                }
                .addOnCompleteListener{
                    /**
                     * Displaying bottom sheet with number of images
                     */
                    val act = activity as CreatePostActivity
                    val bottomSheetDialogFragment = UploadBottomSheet()
                    val bundle = Bundle()
                    bundle.putString("NUM_IMAGES",fileUriList.size.toString())
                    bottomSheetDialogFragment.arguments = bundle
                    bottomSheetDialogFragment.show(act.supportFragmentManager, "sheet")
                }
        }
    }




}