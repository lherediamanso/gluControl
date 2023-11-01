package com.example.glucontrol

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImagenesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val imgView: ImageView =view.findViewById(R.id.rv_image)
    private val textView: TextView =view.findViewById(R.id.rv_text)

    fun render(imagen:Imagenes) {
        textView.text = imagen.nombre
        imgView.setImageResource(imagen.img)
    }
}