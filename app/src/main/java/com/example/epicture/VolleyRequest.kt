package com.example.epicture

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VolleyRequest {
    companion object {
        fun makeRequest(url: String, headers: HashMap<String, String>, context: Context?) : String {
            val queue = Volley.newRequestQueue(context)
            var result = ""

            val stringRequest = object : StringRequest(
                Method.GET, url,
                Response.Listener<String> { response ->
                    result = response
                },
                Response.ErrorListener {
                    Log.e("VOLLEY ERROR", it.message)
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    return headers
                }
            }
            queue.add(stringRequest)
            return result
        }
    }
}