package com.example.epicture

import Utils.Globals
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.CookieManager
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        this.context = this;
        //Title toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val fragmentAdapter = MypagerAdpater(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu:Menu):Boolean {
        val inflater = getMenuInflater()
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
        if (item?.itemId == R.id.SignOut) {
            disconnectBt()
            true
        } else {
            false
        }

    private fun disconnectBt() {
        val cookieManager = CookieManager.getInstance()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookies { aBoolean ->
                Log.d("Cookie removed: ", aBoolean.toString())
                startActivity(Intent(context, MainActivity::class.java))
                finish()
            }
        } else
            cookieManager.removeAllCookie();
    }
}
