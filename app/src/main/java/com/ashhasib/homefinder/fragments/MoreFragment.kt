package com.ashhasib.homefinder.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ashhasib.homefinder.LoginActivity
import com.ashhasib.homefinder.R
import com.ashhasib.homefinder.preference.UserSessionManager

class MoreFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_more, container, false)

        val btn = v.findViewById(R.id.btnLogout) as Button

        btn.setOnClickListener {
            val manager = UserSessionManager(context)
            manager.clear()
            startActivity(Intent(context, LoginActivity::class.java))
            activity!!.finish()
        }


        return v
    }
}