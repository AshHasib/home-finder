package com.ashhasib.homefinder


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ashhasib.homefinder.fragments.HomeFragment
import com.ashhasib.homefinder.fragments.MoreFragment
import com.ashhasib.homefinder.preference.UserSessionManager
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var sessionManager :UserSessionManager

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDatabase.getInstance().getReference("message").setValue("hello as a new message")


        init()


        sessionManager = UserSessionManager(this)
        checkLoginStatus()

        val user = sessionManager.user

        Log.d("USERNAME: ","${user.username}")


        setupBottomNav()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }


    /**
     * Some initial works
     */
    private fun init() {

    }












    private fun setupBottomNav() {

        bottomNavigationView.setOnNavigationItemSelectedListener {
            var fragment: Fragment = HomeFragment()

            when(it.itemId) {

                R.id.nav_home -> {
                    fragment= HomeFragment()
                }

                R.id.nav_search -> {

                }

                R.id.nav_history -> {

                }

                R.id.nav_more -> {
                    fragment = MoreFragment()
                }

            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()

            return@setOnNavigationItemSelectedListener true
        }
    }




    /**
     *
     * Activity methods
     */

    private fun checkLoginStatus() {

        if(!sessionManager.isNotEmpty) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }




    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}
