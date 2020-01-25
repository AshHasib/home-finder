package com.ashhasib.homefinder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ashhasib.homefinder.R


class CreatePostFragment1:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_post1, container, false)

        val btnContinue = view.findViewById(R.id.btnContinue) as Button

        btnContinue.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, CreatePostFragment2()).commit()
        }

        return view
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
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, CreatePostFragment3()).commit()
        }

        return view
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
}