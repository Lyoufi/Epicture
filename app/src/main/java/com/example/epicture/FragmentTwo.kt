package com.example.epicture


import Utils.Globals
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo : Fragment() {
    private val url = "https://api.imgur.com/3/gallery/search?q="
    private val imageLinks = arrayListOf<String>()
    private lateinit var recycler : RecyclerView
    private lateinit var searchBar : EditText
    private val adapter : FragOneAdapter by lazy { FragOneAdapter(this.imageLinks, context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_two, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.recycler = view.findViewById(R.id.account_images)
        this.recycler.layoutManager = GridLayoutManager(context, 2)
        this.recycler.adapter = adapter
        this.searchBar = view.findViewById(R.id.search_bar)
        this.searchBar.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Toast.makeText(context, this.searchBar.text.toString(), Toast.LENGTH_LONG).show()
                if (!this.searchBar.text.isNullOrBlank() || !this.searchBar.text.isNullOrEmpty())
                    searchImages(this.searchBar.text.toString())
            }
            true
        }
    }

    private fun searchImages(query: String) {
        val headers = hashMapOf(Pair("Authorization", "CLIENT-ID e34a8984f58b274"))
        makeRequest(url + query, headers, context)


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
}
