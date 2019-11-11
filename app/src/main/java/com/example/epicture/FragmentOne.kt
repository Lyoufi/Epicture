package com.example.epicture


import Utils.Globals
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass.
 */
class FragmentTree : Fragment() {
    private val url = "https://api.imgur.com/3/account/me/images"
    private val imageLinks = arrayListOf<String>()
    private lateinit var recycler : RecyclerView
    private val adapter : FragOneAdapter by lazy { FragOneAdapter(this.imageLinks, context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_tree, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.recycler = view.findViewById(R.id.account_images)
        this.recycler.layoutManager = GridLayoutManager(context, 2)
        this.recycler.adapter = adapter
        getImges()
    }

    private fun getImges() {
        val headers = hashMapOf(Pair("Authorization", "Bearer ${Globals.token}"))
        makeRequest(url, headers, context)


    }

    private fun makeRequest(url: String, headers: HashMap<String, String>, context: Context?) {
        val queue = Volley.newRequestQueue(context)

        val stringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener<String> { response ->
                val imagesObject : Images = Gson().fromJson<Images>(response, Images::class.java)
                imageLinks.clear()
                for (chaqueImage in imagesObject.data) {
                    imageLinks.add(chaqueImage.link)
                }
                for (item in imageLinks)
                    Log.d("LINKS", item)
                this.adapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Log.e("VOLLEY ERROR", it.message)
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                return headers
            }
        }
        queue.add(stringRequest)
    }

    private fun loadurl(url: String, context: Context?, view: View) {
        val linearLayout = view.findViewById<LinearLayout>(R.id.account_images)
        val imageView = ImageView(context)
        Glide.with(this).load("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg").into(imageView)
        linearLayout.addView(imageView)
    }
}
