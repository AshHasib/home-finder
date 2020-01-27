package com.ashhasib.homefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashhasib.homefinder.fragments.CreatePostFragment1
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() {

    lateinit var description:String
    lateinit var area:String
    lateinit var rent:String
    lateinit var numBedroom:String
    lateinit var numBathroom:String
    lateinit var numFloor:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, CreatePostFragment1())
            .commit()

    }













    /**
     * Methods for getting data from subordinated fragments
     */

    public fun setPartOne(description:String, area:String, rent:String) {
        this.description = description
        this.area = area
        this.rent = rent


        Toast.makeText(this, description, Toast.LENGTH_LONG).show()
    }

    public fun setPartTwo(numBedroom:String, numBathroom:String, numFloor:String) {
        this.numBedroom = numBedroom
        this.numBathroom = numBathroom
        this.numFloor
    }




}
