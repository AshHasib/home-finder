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
import kotlinx.android.synthetic.main.fragment_more.view.*

class MoreFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_more, container, false)

        val btn = v.findViewById(R.id.btnLogout) as Button
        val manager = UserSessionManager(context)

        btn.setOnClickListener {

            manager.clear()
            startActivity(Intent(context, LoginActivity::class.java))
            activity!!.finish()
        }

        displayData(v,manager)


        return v
    }


    public fun displayData(v:View, manager: UserSessionManager) {
        v.txtShowUsername.text = manager.user.username
    }
}