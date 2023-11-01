package com.example.glucontrol

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GlucosaViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val textViewFecha: TextView =view.findViewById(R.id.rv_control_fecha)
    private val textViewValor: TextView =view.findViewById(R.id.rv_control_valor)

    fun render(control:Glucosa) {
        textViewFecha.text = control.fecha
        textViewValor.text = control.valor
    }
}