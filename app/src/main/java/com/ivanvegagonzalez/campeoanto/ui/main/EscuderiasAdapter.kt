package com.ivanvegagonzalez.campeoanto.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivanvegagonzalez.campeoanto.R
import com.ivanvegagonzalez.campeoanto.databinding.ViewEscuderiaBinding
import com.ivanvegagonzalez.campeoanto.inflate
import com.ivanvegagonzalez.campeoanto.loadUrl
import com.ivanvegagonzalez.campeoanto.model.Escuderias


class EscuderiasAdapter(val listener: (Escuderias) -> Unit):
    RecyclerView.Adapter<EscuderiasAdapter.ViewHolder>() {

    var escuderias = emptyList<Escuderias>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_escuderia, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val escuderia = escuderias[position]
        holder.bind(escuderia)

        holder.itemView.setOnClickListener {
            listener(escuderia)
        }
    }

    override fun getItemCount(): Int = escuderias.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewEscuderiaBinding.bind(view)
         fun bind(escuderia: Escuderias){
             binding.title.text = escuderia.nombre

             escuderia.urlImagen?.let { binding.imagen.loadUrl(it) }
         }
    }
}