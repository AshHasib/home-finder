package com.ashhasib.homefinder.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ashhasib.homefinder.CreatePostActivity
import com.ashhasib.homefinder.R
import com.ashhasib.homefinder.authorization.CreatePostManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_create_post1.*
import kotlinx.android.synthetic.main.fragment_create_post2.*
import kotlinx.android.synthetic.main.fragment_create_post2.errorMessage





class RentTypeFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rent_type, container, false)

        val act:CreatePostActivity = activity as CreatePostActivity

        val btnFamily = view.findViewById(R.id.btnFamily) as Button
        val btnBachelor = view.findViewById(R.id.btnBachelors) as Button

        btnFamily.setOnClickListener {
            act.setPostType("FAMILY")
            act
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .replace(R.id.fragmentContainer, CreatePostFragment1())
                .commit()
        }

        btnBachelor.setOnClickListener {
            act.setPostType("BACHELOR")
            act
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CreatePostFragment1())
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .commit()
        }

        return view
    }
}







class CreatePostFragment1:Fragment() {

    lateinit var txtDescription:TextView
    lateinit var txtArea:TextView
    lateinit var txtRent:TextView
    lateinit var errorMessage:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_create_post1, container, false)
        val btnContinue = view.findViewById(R.id.btnContinue) as Button


        txtDescription = view.findViewById(R.id.txtDescription)
        txtArea = view.findViewById(R.id.txtArea)
        txtRent = view.findViewById(R.id.txtRent)
        errorMessage = view.findViewById(R.id.errorMessage)

        btnContinue.setOnClickListener {
            validateAndPass()
        }

        return view
    }






    private fun validateAndPass() {

        val description = txtDescription.text.toString()
        val area = txtArea.text.toString()
        val rent = txtRent.text.toString()

        val manager = CreatePostManager(activity!!)

        if(manager.partOneIsValid(description, area, rent)) {
            errorMessage.visibility = View.GONE
            val act:CreatePostActivity = activity as CreatePostActivity
            act.setPartOne(description = description, area = area, rent = rent)

            act
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .replace(R.id.fragmentContainer, CreatePostFragment2())
                .commit()

        } else {
            errorMessage.visibility = View.VISIBLE
        }

    }

}










class CreatePostFragment2 :Fragment(){

    lateinit var errorMessage:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_post2, container, false)

        val btnNextStep = view.findViewById(R.id.btnNextStep) as Button
        errorMessage = view.findViewById(R.id.errorMessage)

        btnNextStep.setOnClickListener {
            validateAndPass()
        }

        return view
    }








    private fun validateAndPass() {
        val numBedroom = txtNumBedroom.text.toString()
        val numBathroom = txtNumBathroom.text.toString()
        val numFloor = txtNumFloor.text.toString()

        val manager = CreatePostManager(activity!!)

        if(manager.partTwoIsValid(numBedroom, numBathroom, numFloor)) {

            errorMessage.visibility = View.GONE
            val act:CreatePostActivity = activity as CreatePostActivity
            act.setPartTwo(numBedroom, numBathroom, numFloor)

            act
                .supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .replace(R.id.fragmentContainer, CreatePostFragment3())
                .commit()
        }
        else {
            errorMessage.visibility = View.VISIBLE
        }
    }



}










class CreatePostFragment3 :Fragment(){

    private val MIN_IMG_SIZE = 3
    private val RESULT_LOAD_IMAGE:Int = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_post3, container, false)

        val btnUpload = view.findViewById(R.id.btnUpload) as Button

        btnUpload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            intent.action = Intent.ACTION_GET_CONTENT
            activity!!.startActivityForResult(Intent.createChooser(intent,"Select Picture"),RESULT_LOAD_IMAGE)
        }


        val btnSubmit = view.findViewById(R.id.btnSubmit) as Button
        val errorMessage = view.findViewById(R.id.errorMessage) as TextView


        btnSubmit.setOnClickListener {
            val act = activity as CreatePostActivity

            if(act.getTotalImageSize() <MIN_IMG_SIZE) {
                errorMessage.visibility = View.VISIBLE
            }
            else {
                //Upload post
                errorMessage.visibility = View.GONE
                act.uploadPost()
            }
        }
        return view
    }

}



class UploadBottomSheet:BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.upload_bottom_sheet, container,false)

        val uploadMessage = view.findViewById(R.id.uploadMessage) as TextView
        uploadMessage.text = "${arguments!!.getString("NUM_IMAGES")} images uploaded"

        return view
    }
}