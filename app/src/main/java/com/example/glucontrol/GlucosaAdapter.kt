package com.example.glucontrol

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GlucosaAdapter(private val controles: List<Glucosa>): RecyclerView.Adapter<GlucosaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlucosaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_items_glucosa,parent,false)
        return GlucosaViewHolder(view)
    }

    override fun getItemCount() = controles.size

    override fun onBindViewHolder(holder: GlucosaViewHolder, position: Int) {
        holder.render(controles[position])
    }
}