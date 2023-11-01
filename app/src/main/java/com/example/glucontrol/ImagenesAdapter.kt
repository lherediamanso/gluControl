package com.example.glucontrol

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImagenesAdapter  (private val imagenes: List<Imagenes>): RecyclerView.Adapter<ImagenesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_items,parent,false)
        return ImagenesViewHolder(view)
    }

    override fun getItemCount() = imagenes.size

    override fun onBindViewHolder(holder: ImagenesViewHolder, position: Int) {
        holder.render(imagenes[position])
    }
}