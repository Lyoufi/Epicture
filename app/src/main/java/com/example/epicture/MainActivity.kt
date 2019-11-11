package com.example.epicture

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {


    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.Button1)

        button.setOnClickListener {
            startActivity(Intent(this, Webview::class.java))
            finish()
        }
    }

    fun log(view:View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.imgur.com/oauth2/authorize?client_id=9fb3d712426378e&response_type=token&state=APPLICATION_STATE"))
        startActivity(browserIntent)
    }

}
