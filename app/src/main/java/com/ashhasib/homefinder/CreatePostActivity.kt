package com.ashhasib.homefinder


import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ashhasib.homefinder.fragments.CreatePostFragment1
import com.ashhasib.homefinder.model.RentPost
import com.ashhasib.homefinder.model.UploadImage
import com.ashhasib.homefinder.preference.UserSessionManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    lateinit var description:String
    lateinit var area:String
    lateinit var rent:String
    lateinit var numBedroom:String
    lateinit var numBathroom:String
    lateinit var numFloor:String
    lateinit var imgRef:String
    lateinit var currentUserName:String
    lateinit var mainStorageRef:StorageReference

    private val fileNameList:ArrayList<String> = ArrayList()
    private val fileUriList:ArrayList<Uri> = ArrayList()

    var numImages:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)


        val manager = UserSessionManager(this)

        mainStorageRef = FirebaseStorage.getInstance().getReference("img")

        currentUserName = manager.user.username
        imgRef = "${currentUserName}_${System.currentTimeMillis()}"
        Toast.makeText(this,imgRef,Toast.LENGTH_LONG).show()
        Log.d("REFERENCE", imgRef)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, CreatePostFragment1())
            .commit()

    }


    /**
     * Methods for getting data from its fragments
     */

    fun setPartOne(description:String, area:String, rent:String) {
        this.description = description
        this.area = area
        this.rent = rent


        Toast.makeText(this, description, Toast.LENGTH_LONG).show()
    }

    fun setPartTwo(numBedroom:String, numBathroom:String, numFloor:String) {
        this.numBedroom = numBedroom
        this.numBathroom = numBathroom
        this.numFloor = numFloor
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 1 && resultCode === Activity.RESULT_OK) {

            if (data!!.clipData != null) { //Multiple selected

                numImages = data!!.clipData!!.itemCount
                for (i in 0 until numImages) {
                    val fileUri: Uri = data!!.clipData!!.getItemAt(i).uri
                    fileUriList.add(fileUri)
                    val fileName: String = getFileName(fileUri)!!
                    fileNameList.add(fileName)
                }
            } else if (data.data != null) { //single selected

            }
        }
    }






    fun getTotalImageSize():Int{
        return numImages
    }


    /**
     * Upload the post
     */
    fun uploadPost() {

        layoutUpload.visibility = View.VISIBLE
        val rentPost = RentPost(currentUserName,
            description,
            area,
            rent,
            numBedroom,
            numBathroom,
            numFloor,
            imgRef)

        val databaseReference=
            FirebaseDatabase.getInstance().reference.child("img").child(imgRef)

        for(fUri in fileUriList) {
            val fRef = mainStorageRef.child(imgRef).child(getFileName(fUri)!!)

            val uploadTask = fRef.putFile(fUri)
                .addOnSuccessListener {
                    uploadMessage.text = "Upload Complete"
                    val upImg = UploadImage(getFileName(fUri),
                        it.metadata!!.reference!!.downloadUrl.toString())
                    val upId = databaseReference.push().key.toString()
                    databaseReference.child(upId).setValue(upImg)
                }
                .addOnFailureListener{
                    uploadMessage.text = "Failed"
                    Log.d("FAILED MESSAGE", it.toString())
                }
                .addOnProgressListener {
                    uploadMessage.text = "Uploading . . ."
                }
        }


    }




    private fun getFileName(uri: Uri): String? {
        var res: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor? = contentResolver.query(uri,
                null,
                null,
                null,
                null)

            try {
                if (cursor != null && cursor.moveToFirst()) {
                    res = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor!!.close()
            }
        }
        if (res == null) {
            res = uri.path
            val cut = res!!.lastIndexOf('/')
            if (cut != -1) {
                res = res.substring(cut + 1)
            }
        }
        return res
    }

}
