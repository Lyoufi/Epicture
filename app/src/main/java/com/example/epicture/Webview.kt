package com.example.epicture

import Utils.Globals
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

class Webview : AppCompatActivity() {

    private fun split(url: String): String {
        val tmp = url.split("access_token=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val tmp2 = tmp[1].split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return tmp2[0]
    }
    private fun splitid(url: String): String {
        val tmp = url.split("id=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val tmp2 = tmp[1].split("$".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return tmp2[0]
    }
    private lateinit var webView: WebView
    private val context : Context by lazy { this }
    private val url = "https://api.imgur.com/oauth2/authorize?client_id=9fb3d712426378e&response_type=token&state=APPLICATION_STATE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        this.webView = findViewById(R.id.webView)

        this.webView.loadUrl(this.url)
        this.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String
            ): Boolean {
                Log.d("TOKEN", url)
                val tok = split(url)
                val id = splitid(url)
                Log.d("this is a tag", tok)
                Log.d("username id is", id)

                val intent = Intent(context, HomeActivity::class.java)
                Globals.token = tok
                startActivity(intent)
                finish()
                // startactivity //
                return true
            }
        }
    }
}
