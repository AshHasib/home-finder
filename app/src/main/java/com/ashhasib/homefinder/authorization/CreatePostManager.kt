package com.ashhasib.homefinder.authorization

import android.app.Activity
import com.ashhasib.homefinder.model.RentPost
import java.lang.ClassCastException
import java.lang.NumberFormatException

class CreatePostManager(activity: Activity) {

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


    fun isValid(rentPost: RentPost) : Boolean {

        if(rentPost.isEmpty) return false
        else if(rentPost.description.length<10) return false

        try {
            rentPost.rent.toInt()
            rentPost.numBedrooms.toInt()
            rentPost.numBathrooms.toInt()
            rentPost.numFloor.toInt()
        } catch (e:ClassCastException) {
            return false
        }

        return true
    }

}