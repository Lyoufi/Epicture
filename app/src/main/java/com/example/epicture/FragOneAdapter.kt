package com.example.epicture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.single_image_layout.view.*

class FragOneAdapter(private val linksList : List<String>, private val context: Context?) : RecyclerView.Adapter<FragOneAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.imageView
    }

    override fun getItemCount(): Int {
        return this.linksList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.single_image_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = this.linksList[position]

        this.context?.let {
            Glide.with(it)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image)
        }
    }
}