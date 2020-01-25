package com.ashhasib.homefinder.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.ashhasib.homefinder.CreatePostActivity
import com.ashhasib.homefinder.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment: Fragment() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container,false)

        val fabCreatePost = v.findViewById(R.id.fabCreatePost) as FloatingActionButton

        fabCreatePost.setOnClickListener {
            startActivity(Intent(context,CreatePostActivity::class.java))
        }

        return v
    }


}