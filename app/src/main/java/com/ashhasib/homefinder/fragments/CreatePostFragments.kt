package com.ashhasib.homefinder.fragments

import android.content.Context
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


class CreatePostFragment1:Fragment() {

    lateinit var txtDescription:TextView
    lateinit var txtArea:TextView
    lateinit var txtRent:TextView

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



        btnContinue.setOnClickListener {
            validateAndPass()
            activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CreatePostFragment2())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit()
        }

        return view
    }






    private fun validateAndPass() {

        val description = txtDescription.text.toString()
        val area = txtArea.text.toString()
        val rent = txtRent.text.toString()

        val act:CreatePostActivity = activity as CreatePostActivity

        act.setPartOne(description = description, area = area, rent = rent)

    }







}

class CreatePostFragment2 :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_post2, container, false)

        val btnNextStep = view.findViewById(R.id.btnNextStep) as Button



        btnNextStep.setOnClickListener {

            activity!!
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, CreatePostFragment3())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit()
        }

        return view
    }



    private fun validateAndPass() {

    }



}


class CreatePostFragment3 :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_post3, container, false)



        return view
    }



    private fun validateAndPass() {

    }

}