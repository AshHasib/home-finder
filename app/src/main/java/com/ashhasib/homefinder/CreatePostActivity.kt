package com.ashhasib.homefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashhasib.homefinder.fragments.CreatePostFragment1
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, CreatePostFragment1())
            .commit()

    }
}
